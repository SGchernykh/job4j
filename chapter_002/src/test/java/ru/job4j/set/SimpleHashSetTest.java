package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    private SimpleHashSet<String> hashTable = new SimpleHashSet<>();
    private Iterator it = hashTable.iterator();

      @Test
    public void whenAddTwoOriginElementsAndGetSecondElementUsingNext() {
        hashTable.add("Ok");
        hashTable.add("Okey");
        it.next();
        assertThat(it.next(), is("Okey"));
        assertThat(hashTable.remove("Ok"), is(true));
    }

       @Test
    public void whenAddTwoOriginElementAndCheckHasNext() {
        hashTable.add("Ok");
        hashTable.add("Okey");
        it.next();
        assertThat(it.hasNext(), is(true));
    }

        @Test
    public void whenAddTwoNotOriginElementAndCheckHasNext() {
        hashTable.add("Ok");
        hashTable.add("Ok");
        it.next();
        assertThat(it.hasNext(), is(false));

    }

    @Test
    public void whenAddTwoOriginElementsAndRemoveElement() {
        hashTable.add("Ok");
        hashTable.add("Okey");
        assertThat(hashTable.remove("Ok"), is(true));
    }
}