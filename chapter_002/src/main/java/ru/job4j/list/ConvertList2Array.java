package ru.job4j.list;
/**
 * Convert List in two-dimension array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.List;

public class ConvertList2Array {
    /**
     * Method convert List in two-dimension array.
     * @param list The list to convert.
     * @param rows Number of rows in the array.
     * @return
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        int row = 0;
        int column = 1;
        for (int index : list) {
            array[row][column - 1] = index;
            if (column == cells) {
                row++;
                column = 0;
            }
            column++;
        }
        return array;
    }
}