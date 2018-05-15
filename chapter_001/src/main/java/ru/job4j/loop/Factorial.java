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

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
