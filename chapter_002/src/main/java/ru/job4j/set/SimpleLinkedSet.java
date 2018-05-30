package ru.job4j.set;
/**
 * Linked Set.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.list.LinkedStore;

import java.util.Iterator;

public class SimpleLinkedSet<T> implements Iterable<T> {
    LinkedStore<T> set = new LinkedStore<>();

    /**
     * Add element;
     * @param value Value type E.
     * @return True if add element.
     */
    public boolean add(T value) {
        boolean result = false;
        if (!this.set.contains(value)) {
            this.set.addFirst(value);
            result = true;
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return this.set.iterator();
    }
}
