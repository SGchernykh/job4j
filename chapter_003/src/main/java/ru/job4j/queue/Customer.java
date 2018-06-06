package ru.job4j.queue;

import java.util.Queue;

public class Customer implements Runnable {
    SimpleBlockingQueue queue;

    public Customer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 0; index < 25; index++) {
            try {
                System.out.println(queue.poll());
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
