package ru.job4j.buffer;

import ru.job4j.synch.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(7);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        final Thread prod = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        prod.start();
        prod.join();
        consumer.interrupt();
    }
}