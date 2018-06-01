package ru.job4j.tree;

/**
 * Tree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (!findBy(child).isPresent()) {
            Node<E> node = new Node<>(parent);
            if (this.root == null) {
                this.root = node;
                this.root.add(new Node<>(child));
                result = true;
            } else {
                findBy(parent).get().add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Queue<Node<E>> data = new LinkedList<>();
            Queue<Node<E>> result = new LinkedList<>();
            int index = 0;

            @Override
            public boolean hasNext() {
                if (index == 0) {
                    data.offer(root);
                    result.add(root);
                    while (!data.isEmpty()) {
                        Node<E> el = data.poll();
                        for (Node<E> child : el.leaves()) {
                            result.add(child);
                            data.offer(child);
                        }
                    }
                    index = -1;
                }
                return (!result.isEmpty());
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements!");
                }

                return result.poll().toString();
            }
        };
    }
}
