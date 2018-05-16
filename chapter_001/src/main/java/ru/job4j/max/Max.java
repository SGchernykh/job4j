package ru.job4j.max;

/**
 * Search by the program Maximum number.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Max {
    /**
     * Ищет максимальное число из 2х чисел.
     * @param first первое число.
     * @param second второе число.
     * @return максимальное из чисел.
     */

    public int max(int first, int second) {
        return (first > second) ? first : second;
    }
    /**
     * Ищет максимальное число из 3х чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return максимальное из чисел.
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        return max(temp, third);
    }

}
