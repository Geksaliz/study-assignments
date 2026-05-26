package com.koleso;

import com.koleso.java.concurrency.aggregator.DataAggregator;

public class Main {
    public static void main(String[] args) {
        DataAggregator dataAggregator = new DataAggregator();

        System.out.println(dataAggregator.aggregateProductInfo("Ноутбук"));
    }
}
