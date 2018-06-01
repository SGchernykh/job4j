package ru.job4j.tree;

/**
 * Binary Tree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTree<E> implements Iterable<E> {
    private Node<E> root;

    /**
     * Add element in binary tree.
     * @param element Element.
     */
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node<E> current = this.root;
            Node<E> parent;
            while (true) {
                parent = current;
                if (element.hashCode() < parent.value.hashCode()) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Class Node
     * @param <E> Type data.
     */
    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<E> result = new LinkedList<>();
            int index = 0;

            /**
             * symmetrical tree walk.
             * @param root Root tree.
             */
            private void inOrder(Node<E> root) {
                if (root != null) {
                    inOrder(root.left);
                    result.add(root.value);
                    inOrder(root.right);
                }
            }

            @Override
            public boolean hasNext() {
                if (index == 0) {
                    inOrder(root);
                    index = -1;
                }
                return (!result.isEmpty());
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements!");
                }
                return result.poll();
            }
        };
    }
}