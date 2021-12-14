package ru.job4j.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification {%s} to email {%s}", user.getUserName(), user.getEmail());
            String body = String.format("Add a new event to {%s}", user.getUserName());
            System.out.println(subject + " "  + body);
            send(subject, body, user.getEmail());
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        User user = new User("Petr", "petr@mail.com");
        emailNotification.emailTo(user);
        emailNotification.close();
    }
}
