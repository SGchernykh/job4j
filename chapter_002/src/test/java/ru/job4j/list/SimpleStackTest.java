package ru.job4j.list;
/**
 * Test stack.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenPopAndGetInStack() {
        SimpleStack<String> stack = new SimpleStack();
        stack.push("First");
        stack.push("Middle");
        stack.push("Last");
        String result = stack.poll();
        assertThat(result, is("Last"));
        result = stack.poll();
        assertThat(result, is("Middle"));
    }
}