package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlockingQueueTest {
    @Test
    public void checkThreads() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue(5);
        Thread producer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 6; i++) {
                        try {
                            System.out.println("Producer" + i +" run");
                            simpleBlockingQueue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "PRODUCER"
        );
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 6; i++) {
                        try {
                            System.out.println("Consumer" + i +" run");
                            simpleBlockingQueue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "CONSUMER"
        );
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }
}