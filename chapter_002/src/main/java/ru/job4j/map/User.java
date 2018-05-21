package ru.job4j.map;
/**
 * User.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private int id;
    private String name = "";
    private String city = "";

    /**
     * Constructor User class.
     * @param id  User id.
     * @param name User name.
     * @param city User city.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Getter id.
     * @return User id.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter name.
     * @return User name.
     */
    public String getName() {
        return name;
    }




}
