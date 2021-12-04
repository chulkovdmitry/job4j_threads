package ru.job4j.concurrent;

/**
 * Лямбда используется чтобы не создавать анонимный класс внутри потока
 * NEW - нить создана, но не запущена.
 * RUNNABLE - нить запущена и выполняется.
 * BLOCKED - нить заблокирована.
 * WAITING - нить ожидает уведомления.
 * TIMED_WAITING - нить ожидает уведомление в течении определенного периода.
 * TERMINATED - нить завершила работу.
 */

public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
