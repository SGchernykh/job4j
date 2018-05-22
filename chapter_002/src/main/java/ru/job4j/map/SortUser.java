package ru.job4j.map;

import java.util.*;

/**
 * Sort user.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {

    /**
     * Method sort users by age.
     * @param list List users.
     * @return TreeSet.
     */
    public TreeSet<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**z
     * Method sort users by name length.
     * @param list List users.
     * @return Sorted List.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().length() - o2.getName().length();
                    }
                }
        );
        return list;

    }

    /**
     * Method sort by All Fields.
     * @param list List users.
     * @return Sorted List.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        final int rs1 = o1.getName().compareTo(o2.getName());
                        return rs1 != 0 ? rs1 : Integer.compare(o1.getAge(), o2.getAge());
                    }
                }
        );
        return list;
    }
}
