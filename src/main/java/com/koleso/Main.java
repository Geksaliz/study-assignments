package com.koleso;

import com.koleso.java.collection.ArrayMapper;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] intArray = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(intArray));

        Integer[] intMappedArray = ArrayMapper.arrayMapping(intArray, integer -> integer * 2);
        System.out.println(Arrays.toString(intMappedArray));


        String[] stringArray = new String[]{"dog", "home", "cat"};
        System.out.println(Arrays.toString(stringArray));

        String[] stringMappedArray = ArrayMapper.arrayMapping(stringArray, String::toUpperCase);
        System.out.println(Arrays.toString(stringMappedArray));
    }
}
