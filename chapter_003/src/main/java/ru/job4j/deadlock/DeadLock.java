package ru.job4j.deadlock;

/**
 * DeadLock.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DeadLock {
    public static Object lockA = new Object();
    public static Object lockB = new Object();

    /**
     * Constructor.
     */
    public DeadLock() {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();
        threadA.start();
        threadB.start();
    }

    /**
     * Thread A.
     */
    private class ThreadA extends Thread {
        public void run() {
            synchronized (lockA) {
                System.out.println("Thread A: Holding lock B...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread A: Waiting for lock B...");
                synchronized (lockB) {
                    System.out.println("Thread 1: Holding lock A & B...");
                }
            }
        }
    }

    /**
     * Thread B.
     */
    private class ThreadB extends Thread {
        public void run() {
            synchronized (lockB) {
                System.out.println("Thread B: Holding lock B...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread B: Waiting for lock A...");
                synchronized (lockA) {
                    System.out.println("Thread 2: Holding lock A & B...");
                }
            }
        }
    }
}