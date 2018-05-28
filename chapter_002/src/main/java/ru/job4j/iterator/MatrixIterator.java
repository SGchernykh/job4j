package ru.job4j.iterator;

/**
 * Matrix iterator.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {
    /**
     * Input array.
     */
    private final int[][] array;

    /**
     * Outer array inner matrix.
     */
    private int outer = 0;

    /**
     * Inner array inner outer array.
     */
    private int inner = 0;

    /**
     * Constructor.
     *
     * @param array input array.
     */
    public MatrixIterator(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        if (this.array[outer].length <= this.inner) {
            this.inner = 0;
            this.outer++;
        }
        return this.array.length > this.outer;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements!");
        }
        return this.array[outer][inner++];
    }
}