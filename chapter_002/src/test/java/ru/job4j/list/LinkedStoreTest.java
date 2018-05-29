package ru.job4j.list;
/**
 * Linked Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LinkedStoreTest {
    private LinkedStore<Integer> linkedStore = new LinkedStore<>();

    @Before
    public void addFirstAndLast() {
        linkedStore.addFirst(101);
        linkedStore.addLast(102);
        linkedStore.addLast(103);
    }
    @Test
    public void whenAddFirstNewValueAndGetValue() {
        linkedStore.addFirst(123);
        Integer result = linkedStore.get(0);
        assertThat(result, is(123));
    }

    @Test
    public void whenAddFirstAndLastNewValuesGetValue() {
        linkedStore.addLast(123);
        Integer result = linkedStore.get(3);
        assertThat(result, is(123));
    }

    @Test
    public void whenGetNextUseIterator() {
        Iterator iterator = linkedStore.iterator();
        iterator.next();
        Object result = iterator.next();
        assertThat(result, is(102));
    }

    @Test
    public void whenCheckHasNextUseIterator() {
        Iterator iterator = linkedStore.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCheckConcurrentModificationWhenHasNextUseIterator() {
        Iterator iterator = linkedStore.iterator();
        linkedStore.addFirst(100);
        iterator.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCheckNoSuchElementWhenNextUseIterator() {
        Iterator iterator = linkedStore.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}