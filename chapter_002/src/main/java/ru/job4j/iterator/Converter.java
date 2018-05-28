package ru.job4j.iterator;
/**
 * Converter.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;

public class Converter {
    /**
     * @param iteratorIterator - Iterator with iterators.
     */
    private Iterator<Iterator<Integer>> iteratorIterator;
    /**
     * @param iterator - Iterator of a numbers.
     */
    private Iterator<Integer> iterator;

    /**
     * Convert iterator with iterators to iterator with integers.
     * @param it - iterator with iterators.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iteratorIterator = it;
        this.iterator = iteratorIterator.next();
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                if (!iterator.hasNext()) {
                    if (iteratorIterator.hasNext()) {
                        iterator = iteratorIterator.next();
                    }
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!iterator.hasNext()) {
                    if (iteratorIterator.hasNext()) {
                        iterator = iteratorIterator.next();
                    }
                }
                return iterator.next();
            }
        };
    }
}