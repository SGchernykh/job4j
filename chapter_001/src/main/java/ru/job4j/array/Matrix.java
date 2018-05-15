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
        for (int i=1; i <= size; i++){
            for (int j=1; j <= size; j++){
                table[i-1][j-1] = j*i;
            }
        }
        return table;
    }
}