package ru.job4j.hashmap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple Hash Map.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
    private Object[] array = new Object[31];
    private int size = this.array.length;

    /**
     * Insert element in Map.
     * @param key the key.
     * @param value The value.
     * @return True if Add success.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int hash = key.hashCode() % this.size;
        if (!checkSize(hash)) {
            createNewSize();
            this.array[hash] = value;
            result = true;
        }
        if (this.array[hash] == null) {
            this.array[hash] = value;
            result = true;
        }
        return result;
    }

    /**
     * Get element for key.
     * @param key The Keu\y
     * @return Value.
     */
    public V get(K key) {
        int hash = key.hashCode() % this.size;
        return (V) this.array[hash];
    }

    /**
     * Deleted element for key.
     * @param key The Key.
     * @return True if deleted success.
     */
    public boolean delete(K key) {
        boolean result = false;
        int hash = key.hashCode() % this.size;
        if (this.array[hash] != null) {
            this.array[hash] = null;
            result = true;
        }
        return result;
    }

    /**
     * Check Size
     * @return True if the index is not out of bounds.
     */
    private boolean checkSize(int hash) {
        boolean check = false;
        if (hash < this.array.length - 1) {
            check = true;
        }
        return check;
    }

    /**
     * Create new size array.
     */
    private void createNewSize() {
        Object[] newObject = new Object[this.array.length * 2 + 1];
        System.arraycopy(this.array, 0, newObject, 0, this.array.length);
        this.array = newObject;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                while (array[currentIndex] == null && currentIndex < array.length - 1) {
                    currentIndex++;
                }
                return array[currentIndex] != null;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements!");
                }
                return (V) array[currentIndex++];
            }
        };
    }
}
