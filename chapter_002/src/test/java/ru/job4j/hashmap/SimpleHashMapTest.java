package ru.job4j.hashmap;
/**
 * Test Simple Hash Map.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<String, String> map = new SimpleHashMap();

    @Before
    public void addElement() {
        map.insert("test", "value");
        map.insert("test1", "value1");
    }
    @Test
    public void whenAddElementInHashMap() {
        assertThat(map.insert("test2", "value2"), is(true));
        assertThat(map.insert("test1", "value"), is(false));
    }

    @Test
    public void whenGetElementInHashMap() {
        assertThat(map.get("test1"), is("value1"));
    }

    @Test
    public void whenDeleteElementForHashMap() {
        assertThat(map.delete("test1"), is(true));
        assertThat(map.delete("test1"), is(false));
    }
    @Test
    public void whenIteratorElementInHashMap() {
        Iterator<String> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is("value"));
        assertThat(it.hasNext(), is(false));
    }
}