package com.koleso;

import com.koleso.java.stream.collector.Order;
import com.koleso.java.stream.collector.StreamCollector;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("Keyboard", 100.0)
        );

        StreamCollector collector = new StreamCollector();

        System.out.println(collector.collect(orders));
    }
}
