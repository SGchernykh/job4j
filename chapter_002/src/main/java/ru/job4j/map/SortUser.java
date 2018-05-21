package ru.job4j.map;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

}
