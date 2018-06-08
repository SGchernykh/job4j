package ru.job4j.pool;

/**
 * Thread Pool Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void when() {
        int i = 0;
        ThreadPool pool = new ThreadPool();
        while (i != 50) {
            pool.work(new Runnable() {
                @Override
                public void run() {
                    for (int ind = 0; ind < 25; ind++) {
                        for (int b = ind; b != 0; b--) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            i++;
    }
        pool.shutdown();
    }
}