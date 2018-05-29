package ru.job4j.list;

/**
 * Класс SimpleArrayList.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Method for adding an item to the top of the list.
     * @param date Element.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Method Deleted First element.
     * @return deleted item.
     */
    public E delete() {
        Node<E> temp = this.first;
        this.first = this.first.next;
        this.size--;
        return temp.date;
    }

    /**
     * Method get element under index.
     * @param index index element.
     * @return Element.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Method get size.
     * @return Size list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class Node.
     * @param <E> Element type E.
     */
    private static class Node<E> {
        E date;
        Node<E> next;
        Node(E date) {
            this.date = date;
        }
    }
}