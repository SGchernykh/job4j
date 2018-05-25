package ru.job4j.secondary;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.*;

public class Sorted {
    /**
     * Method add  missing folder in directs.
     * @param array List users.
     * @return Sorted List.
     */
    private Set<String> allSet(String[] array) {
        Set<String> set = new TreeSet<>();
        String[] subStr;
        String direct;
        for (String arr : array) {
            direct = "";
            subStr = arr.split("/");
            for (int index = 0; index < subStr.length; index++) {
                if (index != 0) {
                    direct += "/" + subStr[index];
                    set.add(direct);
                } else {
                    set.add(subStr[index]);
                    direct = subStr[index];
                }
            }
        }
        return set;
    }

    /**
     * Method Sorted By Increase.
     * @param array Array string.
     * @return Sorted Set.
     */
    public Set<String> sortByIncrease(String[] array) {
        return allSet(array);
    }

    /**
     * Method sorted by reduction.
     * @param array Array string.
     * @return Set sorted.
     */
    public Set<String> sortByReduction(String[] array) {
        Set<String> set = new TreeSet<>(new SortComporator());
        set.addAll(allSet(array));
        return set;
    }
}