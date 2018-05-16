package ru.job4j.loop;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Factorial {
    /**
     * Вычисление факториала числа.
     * @param n факториал от n.
     * @return результат.
     */
    public int calc(int n) {
        int result = 1;
        for (int number = 1; number <= n; number++) {
            result *= number;
        }
        return result;
    }
}
