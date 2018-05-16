package ru.job4j.array;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Matrix {
    /**
     * Таблица умножения размерости N * N .
     * @param size размерность таблицы.
     * @return Таблица умножения в виде Матрицы.
     */

    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int column = 1; column <= size; column++) {
            for (int line = 1; line <= size; line++) {
                table[column - 1][line - 1] = line * column;
            }
        }
        return table;
    }
}
