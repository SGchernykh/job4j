package ru.job4j.set;
/**
 * Test Linked Set.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddNotOriginValue() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

}