package ru.job4j.array;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Arrays;

public class ArrayDuplicate {
    /**
     * Remove duplicates from an array.
     * @param array Arrays with duplicates.
     * @return Array without duplicates.
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        String variable;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    variable = array[in] ;
                    for (int inIn = in + 1; inIn < unique; inIn++){
                        array[inIn-1] = array[inIn];
                    }
                    array[unique-1] = variable ;
                    unique--;
                    in--;

                }
            }
        }


        return Arrays.copyOf(array, unique);

    }


}
