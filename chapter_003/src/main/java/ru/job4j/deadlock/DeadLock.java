package ru.job4j.deadlock;

/**
 * DeadLock.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DeadLock {
    private  final String name;

    /**
     * Constructor.
     * @param name
     */
    public DeadLock(String name) {
        this.name = name;
    }

    /**
     * Bow
     * @param bower
     */
    public synchronized void bow(DeadLock bower) {
        System.out.format("%s: %s has bowed to me!%n", this.name, bower.name);
        bower.bowBack(this);
    }

    /**
     * Back bow
     * @param bower
     */
    public synchronized void bowBack(DeadLock bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.name, bower.name);
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        final DeadLock alisa = new DeadLock("Alisa");
        final DeadLock bob = new DeadLock("Bob");
        new Thread(() -> alisa.bow(bob)).start();
        new Thread(() -> bob.bow(alisa)).start();
    }
}