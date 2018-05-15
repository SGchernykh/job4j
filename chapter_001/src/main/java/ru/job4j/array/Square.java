package ru.job4j.array;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square {
    /**
     * Заполнение массива квадратами чисел.
     * @param bound Размер массива.
     * @return Массив заполненый квадратами чисел.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        for (int i = 1; i <= bound; i++) rst[i-1] = i*i;
        return rst;
    }
}
