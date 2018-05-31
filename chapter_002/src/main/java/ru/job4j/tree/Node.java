package ru.job4j.tree;

/**
 * Node.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ArrayList;
import java.util.List;

public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    /**
     * Constructor.
     * @param value Root.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Add children.
     * @param child Children.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Children .
     * @return List children.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Equals parents
     * @param that Parents.
     * @return True if discover.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
