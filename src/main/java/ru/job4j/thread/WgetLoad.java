package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetLoad implements Runnable {

    private final String url;
    private final int speed;

    public WgetLoad(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("p.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                int time = speed - bytesRead;
                if (time < 0) {
                    time = speed + bytesRead;
                }
                Thread.sleep(time);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(int argsNum) {
        if (argsNum != 2) {
            throw new IllegalArgumentException("You must provide 2 arguments (file name and speed limit)");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validateArgs(args.length);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetLoad(url, speed));
        wget.start();
        wget.join();
    }
}
