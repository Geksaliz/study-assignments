package com.koleso.java.concurrency.aggregator;

import java.util.Random;

public class MockServices {
    final Random random = new Random();

    public double fetchPrice() {
        someWork();
        probableError();

        return Math.round(random.nextDouble(100) * 100.0) / 100.0;
    }

    public String fetchDescription(String productName) {
        someWork();
        probableError();

        return "Description: " + productName;
    }


    public double fetchRating() {
        someWork();
        probableError();

        return Math.round(random.nextDouble(11) * 10.0) / 10.0;
    }

    private void someWork() {
        int delay = random.nextInt(3000) + 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Поток был прерван во время ожидания");
        }
    }

    private void probableError() {
        if (random.nextInt(100) < 20) {
            throw new RuntimeException("Some error");
        }
    }
}
