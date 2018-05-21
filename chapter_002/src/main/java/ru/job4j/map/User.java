package ru.job4j.map;
/**
 * User.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    private int id;
    private String name = "";
    private String city = "";
    private int age;

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
     * Constructor User class.
     * @param name User name.
     * @param age User age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter Age.
     * @return User age.
     */
    public int getAge() {
        return age;
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

    @Override
    public int compareTo(User p){
        return this.age - p.age;
    }

    @Override
    public String toString() {
        return  name + " " + age;
    }
}
