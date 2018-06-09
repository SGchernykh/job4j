package ru.job4j.сoncurrent;

import org.junit.Test;

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

    @Test(expected =  ru.job4j.сoncurrent.OptimisticException.class)
    public void whenTwoThredUpdateOneBase() {
        NonBlockingCache cache = new NonBlockingCache();
        Base base = new Base(1, "test");
        Base base2 = new Base(2, "test2");
        cache.add(base);
        cache.add(base2);
        new Thread(() -> cache.update(new Base(2, "test " + Thread.currentThread().getName()))).start();
        Thread thread = new Thread(() -> cache.update(new Base(2, "test " + Thread.currentThread().getName())));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}