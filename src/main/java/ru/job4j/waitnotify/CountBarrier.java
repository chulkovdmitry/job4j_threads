package ru.job4j.waitnotify;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 1; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rcount " + i);
                            countBarrier.count();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "FIRST"
        );
        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "SECOND"
        );
        first.start();
        second.start();
    }
}
