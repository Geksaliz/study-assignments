package com.koleso.java.core;

import java.util.ArrayDeque;

public class MyStringBuilder {
    private char[] buffer;
    private int size;
    private final ArrayDeque<Integer> queue;


    public MyStringBuilder() {
        buffer = new char[16];
        size = 0;
        queue = new ArrayDeque<>();

    }

    public MyStringBuilder append(String str) {
        if (str == null) {
            str = "null";
        }

        ensureCapacity(size + str.length());

        for (int i = 0; i < str.length(); i++) {
            buffer[size++] = str.charAt(i);
        }
        queue.add(str.length());

        return this;
    }

    public MyStringBuilder append(char c) {
        ensureCapacity(size + 1);
        buffer[size++] = c;
        queue.add(1);

        return this;
    }

    public int length() {
        return size;
    }

    public char charAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return buffer[index];
    }

    public MyStringBuilder undo() {
        try {
            int undoSize = queue.pollLast();
            char[] newBuffer = new char[buffer.length];
            System.arraycopy(buffer, 0, newBuffer, 0, size - undoSize);
            buffer = newBuffer;
            size -= undoSize;
        } catch (NullPointerException e) {
            System.out.println("Undo operation not be provided");
        }

        return this;
    }

    private void ensureCapacity(int required) {
        if (required > buffer.length) {
            int newCapacity = buffer.length * 2;
            while (newCapacity < required) {
                newCapacity *= 2;
            }

            char[] newBuffer = new char[newCapacity];
            System.arraycopy(buffer, 0, newBuffer, 0, size);
            buffer = newBuffer;
        }
    }

    @Override
    public String toString() {
        return new String(buffer);
    }
}
