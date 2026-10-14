package com.koleso.java.stream.collector;

import java.util.List;
import java.util.Map;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class StreamCollector {
    public List<Map.Entry<String, Double>> collect(List<Order> orders) {
        return orders.stream()
                .collect(groupingBy(Order::product, summingDouble(Order::cost)))
                .entrySet().stream()
                .sorted(comparingByValue(reverseOrder()))
                .limit(3)
                .toList();
    }
}
