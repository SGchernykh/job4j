package ru.job4j.loop;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * Сумма четных числе из диапазона.
     * @param start начало  диапазона.
     * @param finish конец диапазона.
     * @return результат.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int number = start; number <= finish; number++) {
            if (number % 2 == 0) {
                result += number;
            }
        }
        return result;
    }
}