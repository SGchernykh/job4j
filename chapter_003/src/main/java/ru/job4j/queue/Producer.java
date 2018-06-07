package ru.job4j.queue;

/**
 * Producer.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Producer implements Runnable {
     SimpleBlockingQueue queue;

    /**
     * Constructor.
     * @param queue Queue.
     */
    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int index = 0; index < 25; index++) {
            try {
                queue.offer(index);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}