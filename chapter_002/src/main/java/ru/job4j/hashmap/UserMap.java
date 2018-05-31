package ru.job4j.hashmap;

/**
 * User Map.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.HashMap;

public class UserMap {
    HashMap<User, Object> map = new HashMap<>();

    /**
     * Add user map.
     * @param user User.
     * @param object Value.
     */
    public void add(User user, Object object) {
        map.put(user, object);
    }

    /**
     * Display element map.
     */
    public void displayMap() {
        System.out.println(map);
    }
}