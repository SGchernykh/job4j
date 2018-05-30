package ru.job4j.list;

/**
 * Test Queue.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenAddAndGetFromQueue() {
        SimpleQueue<String> queue = new SimpleQueue();
        queue.push("First");
        queue.push("Middle");
        queue.push("Last");
        assertThat(queue.poll(), is("First"));
        assertThat(queue.poll(), is("Middle"));
    }
}