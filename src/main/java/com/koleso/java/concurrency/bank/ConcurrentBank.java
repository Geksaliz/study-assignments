package com.koleso.java.concurrency.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

public class ConcurrentBank {
    private final Map<Integer, BankAccount> bankAccounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(BigDecimal initialBalance) {
        final BankAccount bankAccount = new BankAccount(initialBalance);

        if (bankAccounts.putIfAbsent(bankAccount.getLockId(), bankAccount) != null) {
            throw new IllegalStateException("Duplicate id");
        }

        return bankAccount;
    }

    public void transfer(
            BankAccount depositAccount,
            BankAccount withdrawAccount,
            BigDecimal amount
    ) {
        if (depositAccount == null) throw new IllegalArgumentException("Deposit account cannot be null");
        if (withdrawAccount == null) throw new IllegalArgumentException("Withdraw account cannot be null");
        if (depositAccount == withdrawAccount) return;

        Lock firstLock;
        Lock secondLock;
        if (depositAccount.getLockId() > withdrawAccount.getLockId()) {
            firstLock = depositAccount.getLock();
            secondLock = withdrawAccount.getLock();
        } else {
            secondLock = depositAccount.getLock();
            firstLock = withdrawAccount.getLock();
        }

        firstLock.lock();
        secondLock.lock();
        try {
            withdrawAccount.withdraw(amount);
            depositAccount.deposit(amount);
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    // Дорого из-за блокировки всех счетов (т.к. баланс может меняться из вне), но гарантия корректного результата
    public BigDecimal getTotalBalance() {
        final List<BankAccount> accounts = new ArrayList<>(bankAccounts.values());
        accounts.sort(Comparator.comparingInt(BankAccount::getLockId));

        for (BankAccount account : accounts) {
            account.getLock().lock();
        }

        try {
            return accounts.stream()
                    .map(BankAccount::getBalance)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } finally {
            for (int i = accounts.size() - 1; i >= 0; i--) {
                accounts.get(i).getLock().unlock();
            }
        }
    }
}
