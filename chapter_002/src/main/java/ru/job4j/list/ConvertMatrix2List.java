package ru.job4j.list;
/**
 * Converts two-dimension array in List.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {

    /**
     * Method convert two-dimension array in List.
     * @param array two-dimension array.
     * @return List.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int column : row) {
                list.add(column);
            }
        }
        return list;
    }
}