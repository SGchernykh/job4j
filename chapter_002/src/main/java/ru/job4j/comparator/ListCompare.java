package ru.job4j.comparator;

/**
 * Implementation Comparator for strings.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    /**
     * Method rules for comparisons.
     * @param left comparison element.
     * @param right comparison element.
     * @return  0 equals, -1 right element more, 1  left element more.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        final int lef = left.length();
        final int rig = right.length();
        for (int index = 0; index < left.length(); index++) {
            if (left.charAt(index) > right.charAt(index)) {
                result = 1;
                break;
            } else {
                if (left.charAt(index) < right.charAt(index)) {
                    result = -1;
                    break;
                }
            }
        }
        if ((result == 0) && (lef < rig)) {
            result = -1;
        }
        return result;
    }
}