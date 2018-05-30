package ru.job4j.set;
/**
 * Simple set.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.list.DynamicArray;
import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private DynamicArray<T> array = new DynamicArray<>(5);

    /**
     * Add element;
     * @param value Value type E.
     * @return True if add element.
     */
    public boolean add(T value) {
        boolean result = false;
        if (this.array.contains(value)) {
            this.array.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return this.array.iterator();
    }
}