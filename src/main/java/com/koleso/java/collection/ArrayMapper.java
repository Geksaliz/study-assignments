package com.koleso.java.collection;

import java.util.Arrays;
import java.util.function.Function;

public class ArrayMapper {

    public static <T> T[] arrayMapping(T[] array, Function<T, T> function) {
        T[] result = Arrays.copyOf(array, array.length);

        for (int i = 0; i < array.length; i++) {
            result[i] = function.apply(array[i]);
        }

        return result;
    }
}
