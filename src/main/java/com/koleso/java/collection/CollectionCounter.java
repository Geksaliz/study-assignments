package com.koleso.java.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectionCounter {
    public static <T> Map<T, Integer> count(Collection<T> collection) {
        final Map<T, Integer> map = new HashMap<>();
        for (T t : collection) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        return map;
    }
}
