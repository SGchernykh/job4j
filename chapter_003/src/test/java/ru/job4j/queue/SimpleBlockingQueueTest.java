package ru.job4j.queue;

/**
 * SimpleBlockingQueue.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

    @Test
    public void when() {
    int count = 0;
        /**
         * Producer.
         */
        Thread producer = new Thread(new Producer(queue));

        /**
         * Customer.
         */
        Thread customer = new Thread(new Customer(queue));
        customer.start();
        producer.start();
        try {
            customer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}