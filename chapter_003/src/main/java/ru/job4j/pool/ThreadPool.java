package ru.job4j.pool;
/**
 * Thread Pool.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@ThreadSafe
public class ThreadPool {
    @GuardedBy("this")
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private final List<Thread> threads = new LinkedList<>();
    private int size = Runtime.getRuntime().availableProcessors();
    private boolean isRunning = true;

    /**
     * Constructor.
     */
    public ThreadPool() {
        for (int i = 0; i < this.size; i++) {
            threads.add(new Thread(new Perform()));
            threads.get(i).start();
        }
    }

    /**
     * Add work.
     * @param job Job.
     */
    public synchronized void work(Runnable job) {
        tasks.offer(job);
        notifyAll();
    }

    /**
     * Close.
     */
    public  void shutdown() {
      while (!tasks.isEmpty()) {
          System.out.println("Waiting for execution " + this.tasks.size());
      }
        isRunning = false;
    }

    /**
     * isEmty.
     */
    public synchronized void isEmty() {
        if (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Flow work.
     */
    private final class Perform implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = tasks.poll();
                isEmty();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}