package com.koleso.java.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<E> {
    private final Queue<E> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();

        while (queue.size() == capacity) {
            wait();
        }

        queue.offer(e);
        notifyAll();
    }

    public synchronized E dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        E result = queue.poll();
        notifyAll();

        return result;
    }

    public synchronized int size() {
        return queue.size();
    }
}
