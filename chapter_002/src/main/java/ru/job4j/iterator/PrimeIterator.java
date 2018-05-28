package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    /**
     * @param array - array with numbers.
     */
    private int[] array;
    /**
     * @param index - index for array.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param array - array with numbers.
     */
    public PrimeIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        int count = 0;
        for (int iter = index; iter < this.array.length; iter++) {
            if (!result) {
                count = 0;
                for (int ind = 2; ind <= this.array[iter]; ind++) {
                    if (this.array[iter] % ind == 0) {
                        this.index = iter;
                        result = true;
                        count++;
                    }
                    if (count > 1) {
                        result = false;
                        break;
                    }
                }
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
