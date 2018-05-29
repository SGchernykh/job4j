package ru.job4j.generic;
/**
 * Test shell over array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {

    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test");
        String result = simpleArray.get(0);
        assertThat(result, is("test"));
    }

    @Test
    public void whenTypeIntShouldReturnInt() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(2);
        int result = simpleArray.get(0);
        assertThat(result, is(2));
    }

    @Test
    public void whenSetNumber() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(2);
        simpleArray.set(0, 7);
        int result = simpleArray.get(0);
        assertThat(result, is(7));
    }

    @Test
    public void whenDeleteNumber() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.delete(1);
        int result = simpleArray.get(1);
        assertThat(result, is(3));
    }

    @Test
    public void whenWentBeyond() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        assertThat(simpleArray.add(3), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenBeyondTheBoundsOfTheArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        Iterator<Integer> it  = simpleArray.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}