package ru.job4j.servlets;
/**
 * Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.List;

public interface Store {

    /**
     * Add element.
     * @param name Name.
     * @param login Login.
     * @param email Email.
     */
    void add(String name, String login, String email);

    /**
     * Update element with id.
     * @param id Id.
     * @param name Name.
     * @param login Login.
     * @param email Email.
     */
    void update(int id, String name, String login, String email);

    /**
     * Deleted element with id.
     * @param id
     */
    void delete(int id);

    /**
     * Find All
     * @return list
     */
    List<Users> findAll();

    /**
     * Find By Id.
     * @param id id.
     * @return User.
     */
    Users findById(int id);
}
