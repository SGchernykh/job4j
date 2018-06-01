package ru.job4j.tree;

/**
 * Binary Tree Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinaryTreeTest {
    @Test
    public void whatNewBinaryTreePassIteratorNextAndHashNext() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(4);
        tree.add(15);
        tree.add(7);
        tree.add(3);
        tree.add(6);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(15));
        assertThat(it.hasNext(), is(false));
    }
}