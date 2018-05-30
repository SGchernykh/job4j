package ru.job4j.list;

/**
 * Node Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void whenNodeHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        boolean result = first.hasCycle(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenNodeHasNotCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        boolean result = first.hasCycle(first);
        assertThat(result, is(false));
    }
}