package ru.job4j.tree;

/**
 * Tree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

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
        return null;
    }
}
