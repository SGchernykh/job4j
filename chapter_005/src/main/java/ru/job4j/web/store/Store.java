package ru.job4j.web.store;
/**
 * Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.City;
import ru.job4j.web.model.Country;
import ru.job4j.web.model.Role;
import ru.job4j.web.model.Users;

import java.util.List;

public interface Store {


    void add(Users users);

    /**
     * Update element with id.
     * @param id Id.
     * @param name Name.
     * @param login Login.
     * @param email Email.
     */
    void update(Users users);

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

    Users getUserByLoginAndPassword(final String login, final String password);

    List<Role> roleAll();

    List<Country> countryAll();

    List<City> cityByNameCountry(Integer idCountry);

    Country countryByName(String name);

    Role roleByName(String name);

    City cityByName(String name);
}
