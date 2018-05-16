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
        for (int index = 1; index <= bound; index++) rst[index-1] = index*index;
        return rst;
    }
}
