package ru.job4j.optimistic;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NonBlockingCacheTest {
    @Test
    public void whenAddTwoBaseResultSizeTwo() {
        NonBlockingCache cache = new NonBlockingCache();
        Base base = new Base(1, "test");
        Base base2 = new Base(2, "test2");
        cache.add(base);
        cache.add(base2);
        assertThat(cache.size(), is(2));
    }

    @Test
    public void whenDeletedTwoBaseResultSizeOne() {
        NonBlockingCache cache = new NonBlockingCache();
        Base base = new Base(1, "test");
        Base base2 = new Base(2, "test2");
        cache.add(base);
        cache.add(base2);
        cache.delete(base2);
        assertThat(cache.size(), is(1));
    }

    @Test
    public void whenTwoThreadUpdateOneBase() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        NonBlockingCache cache = new NonBlockingCache();
        Base base = new Base(1, "test");
        Base base2 = new Base(2, "test2");
        cache.add(base);
        cache.add(base2);
        Thread.UncaughtExceptionHandler exception = (th, ex) -> System.out.println(ex);
        new Thread(() -> cache.update(new Base(2, "test " + Thread.currentThread().getName()))).start();
        Thread thread = new Thread(() -> cache.update(new Base(2, "test " + Thread.currentThread().getName())));
        thread.setUncaughtExceptionHandler(exception);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(new String(out.toByteArray()), is("ru.job4j.сoncurrent.OptimisticException: Versions do not match!\r\n"));

    }
}