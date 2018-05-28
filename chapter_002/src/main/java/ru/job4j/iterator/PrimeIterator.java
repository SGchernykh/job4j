package ru.job4j.iterator;
/**
 * Test Iterator of prime numbers in array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    /**
     * @param array - Array with numbers.
     */
    private int[] array;
    /**
     * @param index - The index is a Prime number.
     */
    private int index = 0;

    /**
     * Prime number iterator.
     * @param array - Array numbers.
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
                for (int number = 2; number <= this.array[iter]; number++) {
                    if (this.array[iter] % number == 0) {
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