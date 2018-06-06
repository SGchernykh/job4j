package ru.job4j.queue;

import java.util.Random;

public class Producer implements Runnable {
     SimpleBlockingQueue queue;
    Random random = new Random();

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 0; index < 50; index++) {
            try {
                queue.offer(index);
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
