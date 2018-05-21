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
        int cells = (int) (Math.ceil(((double) list.size() / rows)));
        int[][] array = new int[rows][cells];
        int size = list.size();
        int index = 1;
        for (int[] row : array) {
            for (int column = 0; column < cells; column++) {
                if (index <= size) {
                    row[column] = list.get(index - 1);
                } else {
                    row[column] = 0;
                }
                index++;
            }
        }
        return array;
    }
}