package ru.job4j.list;
/**
 * Linked Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStore<E> implements Iterable<E> {
    private int index = 0;
    private Node<E> first;
    private Node<E> last;

    /**
     * Constructor.
     */
    public LinkedStore() {
        this.first = null;
        this.last = null;
    }

    /**
     * Check is empty.
     * @return True if list empty.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Add element in first.
     * @param model Element type E.
     */
    public void addFirst(E model) {
        Node<E> newNod = new Node<>(model);
        if (isEmpty()) {
            this.last = newNod;
        }
        newNod.next = this.first;
        this.first = newNod;
        this.index++;
    }

    /**
     * Add element in last.
     * @param model Element type E
     */
    public void addLast(E model) {
        Node<E> newNod = new Node<>(model);
        if (isEmpty()) {
            this.first = newNod;
        } else {
            this.last.next = newNod;
        }
        this.last = newNod;
        this.index++;
    }

    /**
     * Get element with index.
     * @param index Index element.
     * @return Element type E.
     */
    public E get(int index) {
        int iter = 0;
        Node<E> find = this.first;
        while (iter != index) {
            find = find.next;
            iter++;
        }
        return find.date;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int modCount = index;
            private int currentIndex = 0;
            Node<E>  copy = first;
            Node<E>  temporary;

            @Override
            public boolean hasNext() {
                if (modCount != index) {
                    throw new ConcurrentModificationException("Modification array! Create new Iterator");
                }
                return (currentIndex < index);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements!");
                }
                if (copy.next != null) {
                    currentIndex++;
                    temporary = copy;
                    copy = copy.next;
                    return temporary.date;
                }
                currentIndex++;
                return copy.date;
            }
        };
    }

    /**
     * Class Node
     * @param <E> Type data.
     */
    private static class Node<E> {
        E date;
        Node<E> next;
        Node(E date) {
            this.date = date;
        }
    }
}

