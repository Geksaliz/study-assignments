package com.koleso.java.concurrency.aggregator;

import java.util.concurrent.CompletableFuture;

public class DataAggregator {
    private final MockServices mockServices;

    public DataAggregator() {
        this.mockServices = new MockServices();
    }

    public ProductInfo aggregateProductInfo(String productName) {
        CompletableFuture<Double> priceTask = CompletableFuture.supplyAsync(mockServices::fetchPrice)
                .exceptionally(ex -> 0.0);
        CompletableFuture<String> descriptionTask = CompletableFuture.supplyAsync(() ->
                mockServices.fetchDescription(productName)
        ).exceptionally(ex -> "Нет данных");
        CompletableFuture<Double> ratingTask = CompletableFuture.supplyAsync(mockServices::fetchRating)
                .exceptionally(ex -> 0.0);

        return CompletableFuture.allOf(priceTask, descriptionTask, ratingTask)
                .thenApply(v -> {
                    double price = priceTask.join();
                    double rating = ratingTask.join();
                    String description = descriptionTask.join();

                    return new ProductInfo(productName, price, description, rating);
                })
                .exceptionally(ex -> new ProductInfo(productName, 0.0, "Нет данных", 0.0))
                .join();
    }
}
