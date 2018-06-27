package ru.job4j.web.store;

/**
 * Validate.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.Users;

import java.util.List;

public interface Validate {

    /**
     * Add element.
     * @param name Name.
     * @param login Login.
     * @param email Email.
     */
    boolean add(Users user);

    /**
     * Update element with id.
     * @param id Id.
     * @param name Name.
     * @param login Login.
     * @param email Email.
     */
    boolean update(Users users);

    /**
     * Deleted element with id.
     * @param id
     */
    boolean delete(int id);

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
