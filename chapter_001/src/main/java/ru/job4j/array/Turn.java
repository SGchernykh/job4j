package ru.job4j.array;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {

    public int[] back(int[] array) {
        for (int i = 0; i < array.length/2; i++) {
            int temp = array[i];

            array[i] = array[array.length - 1 -i];
            array[array.length - 1 -i] = temp;
        }
        return array;
    }
}
