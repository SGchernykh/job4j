package ru.job4j.queue;

/**
 * Customer.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Customer implements Runnable {
    SimpleBlockingQueue queue;

    /**
     * Constructor.
     * @param queue Queue.
     */
    public Customer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 0; index < 25; index++) {
            try {
                System.out.println(queue.poll());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}