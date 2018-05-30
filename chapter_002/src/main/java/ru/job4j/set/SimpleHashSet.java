package ru.job4j.set;

/**
 * Simple Hash Set.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;

public class SimpleHashSet<E> implements Iterable<E> {
    private Object[] objects;

    /**
     * Constructor.
     */
    public SimpleHashSet() {
        this.objects = new Object[10];
    }

    /**
     * Add element.
     * @param value - new element.
     */
    public boolean add(E value) {
        boolean result = false;
        int arrayIndex = value.hashCode() % this.objects.length;
        if (checkSize(arrayIndex)) {
            createNewSize(arrayIndex);
        }
        if (contains(value)) {
            objects[arrayIndex] = value;
            result = true;
        }
        return result;
    }

    /**
     * Remove element.
     * @param value Element.
     * @return True if element remove.
     */
    public boolean remove(E value) {
        int arrayIndex = value.hashCode() % this.objects.length;
        this.objects[arrayIndex] = null;
        return this.objects[arrayIndex] == null;
    }

    /**
     * Check for originality.
     * @param value Value.
     * @return True if value originality.
     */
    private boolean contains(E value) {
        int valueIndex = value.hashCode() % this.objects.length;
        return (objects[valueIndex] != null && objects[valueIndex].equals(value)) ? false : true;
    }

    /**
     * Check Size
     * @return True if the index is not out of bounds.
     */
    private boolean checkSize(int arrayIndex) {
        if (arrayIndex > objects.length - 1) {
            return true;
        }
        return false;
    }

    /**
     * Create new size for container.
     */
    private void createNewSize(int newSize) {
        Object[] newObject = new Object[newSize + 2];
        System.arraycopy(objects, 0, newObject, 0, objects.length);
        this.objects = newObject;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                while (objects[currentIndex] == null && currentIndex < objects.length - 1) {
                    currentIndex++;
                }
                return objects[currentIndex++] != null ? true : false;
            }

            @Override
            public E next() {
                while (objects[currentIndex] == null && currentIndex < objects.length - 1) {
                    currentIndex++;
                }
                return (E) objects[currentIndex++];
            }
        };
    }
}