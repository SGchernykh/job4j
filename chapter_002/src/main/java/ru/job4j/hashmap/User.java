package ru.job4j.hashmap;

/**
 * User.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Constructor User.
     * @param name Name.
     * @param children Children.
     * @param birthday Birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}