package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println("first " + Thread.currentThread().getName())
        );
        first.start();
        Thread second = new Thread(
                () -> System.out.println("second " + Thread.currentThread().getName())
        );
        second.start();

        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("First: " + first.getState());
            System.out.println("Second: " + second.getState());
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " IS TERMINATED");
    }
}
