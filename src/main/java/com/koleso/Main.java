package com.koleso;

import com.koleso.java.collection.CollectionCounter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(CollectionCounter.count(List.of("1", "2", "2", "3", "3", "3", "4", "4", "4", "4")));
    }
}
