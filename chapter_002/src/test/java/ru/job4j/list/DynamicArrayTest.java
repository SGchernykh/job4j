package ru.job4j.list;
/**
 * Test Dynamic Array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DynamicArrayTest {
    private DynamicArray<Integer> dynamicArray = new DynamicArray(2);
    private Iterator<Integer> it;
    @Before
    public void addArray() {
        this.dynamicArray.add(101);
        this.dynamicArray.add(102);
        this.dynamicArray.add(103);
    }
    @Test
    public void whenAddNewValue() {
        this.dynamicArray.add(123);
        Integer result = this.dynamicArray.get(3);
        assertThat(result, is(123));
    }

    @Test
    public void whenAddThreeNewValues() {
        Integer result = this.dynamicArray.get(2);
        assertThat(result, is(103));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModification() {
        this.it = this.dynamicArray.iterator();
        this.dynamicArray.add(104);
        this.it.next();
    }
}