package ru.job4j.generic;
/**
 * shell over array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int index = 0;
    private int iter = 0;

    /**
     * Constructor.
     * @param size Size arrays.
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Method add elements in array.
     * @param model Element to add.
     */
    public boolean add(T model) {
        if (index < this.array.length) {
            this.array[this.index++] = model;
            return true;
        }
        return false;
    }

    /**
     * Method set element with index.
     * @param index Index element.
     * @param model New element.
     */
    public void set(int index, T model) {
        this.array[index] = model;
    }

    /**
     * Method deleted element with index.
     * @param index Index element.
     */
    public void delete(int index) {
        int positionNew = index + 1;
        System.arraycopy(array, positionNew, array, index, array.length - (index + 1));
    }

    /**
     * Method get element with index.
     * @param index Index element.
     * @return The element type is T.
     */
    public T get(int index) {
        return (T) this.array[index];
    }

    /**
     * Iterator.
     * @return Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = index;

            @Override
            public boolean hasNext() {
                return (iter < this.count);
            }

            @Override
            public T next() {
                if (iter < this.count) {
                    return (T) array[iter++];
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }
        };
    }
}
