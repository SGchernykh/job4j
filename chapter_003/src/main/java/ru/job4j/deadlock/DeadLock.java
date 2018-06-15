package ru.job4j.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * DeadLock.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DeadLock {
    volatile ReentrantLock[] mas = new ReentrantLock[2];

    public DeadLock() {
        for (int index = 0; index < mas.length; index++) {
            mas[index] = new ReentrantLock();
        }
    }

    public void taking() {
        try {
            if (mas[0].tryLock()) {
                if (mas[1].tryLock()) {
                    mas[0].unlock();
                }
            } else {
                if (mas[1].tryLock()) {

                    if (mas[0].tryLock()) {
                        mas[1].unlock();
                    }
                }
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        Thread alisa = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadLock.taking();

            }
        });
        Thread bob = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                deadLock.taking();
            }
        });
        alisa.start();
        bob.start();
    }
}