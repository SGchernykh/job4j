package ru.job4j.switcher;

/**
 * Switcher.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Switcher {
    @GuardedBy("this")
    private StringBuilder string = new StringBuilder();
    private volatile boolean flag = false;

    /**
     * Constructor.
     */
    public Switcher() {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();
        threadA.start();
        threadB.start();
    }

    /**
     * Add String.
     * @param number Number.
     */
    private void addString(int number) {
        this.string.append(String.valueOf(number));
    }

    /**
     * Get
     * @return
     */
    public synchronized String getString() {
        return this.string.toString();
    }

    private class ThreadA extends Thread {
        public void run() {
            while (!Thread.interrupted()) {
                while (!flag) {
                    for (int count = 0; count < 10; count++) {
                        addString(1);
                        System.out.println(string);
                    }
                    flag = true;
                }
            }
        }
    }

    /**
     * Thread B.
     */
    private class ThreadB extends Thread {
        public void run() {
            while (!Thread.interrupted()) {
                while (flag) {
                    for (int count = 0; count < 10; count++) {
                        addString(2);
                        System.out.println(string);
                    }
                    flag = false;
                }
            }
        }
    }
}