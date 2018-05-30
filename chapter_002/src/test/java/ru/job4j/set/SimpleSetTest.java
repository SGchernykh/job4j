package ru.job4j.set;
/**
 * Simple set Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNotOriginValue() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}