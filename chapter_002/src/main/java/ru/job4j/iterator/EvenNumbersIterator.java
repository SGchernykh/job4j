package ru.job4j.iterator;
/**
 * Iterator of even numbers in array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    /**
     * Input array.
     */
    private final int[] array;

    /**
     * Index even numbers in array.
     */
    private int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int ind = index; ind < this.array.length; ind++) {
            if (this.array[ind] % 2 == 0) {
                this.index = ind;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements!");
        }
        return this.array[index++];
    }
}
