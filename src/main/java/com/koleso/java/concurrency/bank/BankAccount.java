package com.koleso.java.concurrency.bank;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    Блокировки на deposit, withdraw и getBalance выглядят избыточными, но т.к. эти методы сами по себе должны быть
    потокобезопасными (исходя из условий задачи и потому что, теоретически, могут быть вызваны из другого места),
    я вставил эти блокировки. Более того, ReentrantLock является реентерабельным, поэтому проблем это не вызовет.
 */
public class BankAccount {
    private final ReentrantLock lock = new ReentrantLock();
    private final int lockId;
    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        this.balance = balance;
        this.lockId = System.identityHashCode(this);
    }

    public void deposit(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        if (amount.signum() <= 0) throw new IllegalArgumentException("Amount cannot be negative");

        lock.lock();
        try {
            balance = balance.add(amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount cannot be null");
        if (amount.signum() <= 0) throw new IllegalArgumentException("Amount cannot be negative");

        lock.lock();
        try {
            if (balance.compareTo(amount) < 0) {
                throw new IllegalStateException("Insufficient balance");
            }

            balance = balance.subtract(amount);
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public int getLockId() {
        return lockId;
    }

    public Lock getLock() {
        return lock;
    }
}
