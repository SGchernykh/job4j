package ru.job4j.list;
/**
 * Stack.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<E> implements InterfaceSimple<E> {
   private LinkedStore<E> linkedStore = new LinkedStore<>();

    @Override
    public E poll() {
        return this.linkedStore.deleteFirst();
    }

    @Override
    public void push(E value) {
        this.linkedStore.addFirst(value);
    }
}