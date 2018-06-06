package ru.job4j.queue;
/**
 * SimpleBlockingQueue.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int maxSize;
    private boolean flag;

    /**
     * Constructor.
     * @param maxSize Max size.
     */
    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.flag = false;
    }

    /**
     * Add value.
     * @param value Value.
     */
    public synchronized void offer(T value) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.queue.add(value);
        notifyAll();
        if (this.queue.size() == this.maxSize) {
            flag = true;
        }
    }

    /**
     * Deleted element.
     * @return Element.
     */
    public synchronized T poll() {
        while (this.queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T value = this.queue.poll();
        if (this.queue.size() != this.maxSize) {
            this.flag = false;
            notifyAll();
        }
        return value;
    }
}