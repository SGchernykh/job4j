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
     * @param list List users
     * @return TreeSet
     */
    public Set<User> sort (List<User> list) {
        TreeSet<User> result = new TreeSet<>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
    public List<User> sortNameLength (List<User> list) {
        List<User> result = new ArrayList<>();
        result.addAll(list);
        result.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().length() - o2.getName().length();
                    }
                }
        );
        return result;

    }

    public List<User> sortByAllFields (List<User> list) {
        List<User> result = new ArrayList<>();
        result.addAll(list);
        result.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        final int rs1 = o1.getName().compareTo(o2.getName());
                        return rs1 != 0 ? rs1 : Integer.compare(o1.getAge(), o2.getAge());
                    }
                }
        );
        return result;
    }
}
