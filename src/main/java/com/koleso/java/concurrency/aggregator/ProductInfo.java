package com.koleso.java.concurrency.aggregator;

public record ProductInfo(
        String name,
        double price,
        String description,
        double rating
) {
}
