package ru.job4j.jcip;

import org.junit.Assert;
import org.junit.Test;

public class UserStorageTest {

    @Test
    public void add() {
        UserStorage userStorage = new UserStorage();
        User userTest = new User(1, 200);
        Assert.assertTrue(userStorage.add(userTest));
    }

    @Test
    public void update() {
        UserStorage userStorage = new UserStorage();
        User first = new User(1, 100);
        User second = new User(1, 200);
        Assert.assertTrue(userStorage.add(first));
        Assert.assertTrue(userStorage.update(second));
    }

    @Test
    public void delete() {
        UserStorage userStorage = new UserStorage();
        User first = new User(1, 100);
        Assert.assertTrue(userStorage.add(first));
        Assert.assertTrue(userStorage.delete(first));
    }

    @Test
    public void transfer() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 100));
        userStorage.add(new User(2, 200));
        Assert.assertTrue(userStorage.transfer(1, 2, 50));
    }

    @Test
    public void thread() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        Assert.assertTrue(userStorage.add(new User(i, 100)));
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        Assert.assertTrue(userStorage.add(new User(i + 11, 100)));
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
} 