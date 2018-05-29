package ru.job4j.list;
/**
 * Dynamic Array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicArray<E> implements Iterable {
    private Object[] object;
    private int index = 0;
    private int count = 0;

    /**
     * Constructor.
     * @param size Size array.
     */
    public DynamicArray(int size) {
        this.object = new Object[size];
    }

    /**
     * Add element;
     * @param value Value type E.
     * @return True if add element.
     */
    public boolean add(E value) {
        if (checkSize()) {
            this.object[index++] = value;
            this.count++;
        } else {
            createNewSize();
            this.object[index++] = value;
            this.count++;
        }
        return true;
    }

    /**
     * Get element with index.
     * @param index Index element.
     * @return Element type E.
     */
    public E get(int index) {
        return (E) this.object[index];
    }

    /**
     * Check Size
     * @return True if the index is not out of bounds.
     */
    private boolean checkSize() {
        boolean check = false;
        if (index < object.length - 1) {
            check = true;
        }
        return check;
    }

    /**
     * Create new size array.
     */
    private void createNewSize() {
        Object[] newObject = new Object[this.object.length * 2];
        System.arraycopy(this.object, 0, newObject, 0, this.object.length);
        this.object = newObject;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int modCount = count;
            int iter = 0;

            @Override
            public boolean hasNext() {
                if (modCount == count) {
                    return (iter < index);
                } else {
                    throw new ConcurrentModificationException("Modification array! Create new Iterator");
                }
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return (E) object[iter++];
                } else {
                    throw new ConcurrentModificationException("Modification array! Create new Iterator");
                }
            }
        };
    }
}
