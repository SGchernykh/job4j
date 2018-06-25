package ru.job4j.servlets;

/**
 * Users.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Date;

public class Users {
    private final int id;
    private  String name;
    private  String login;
    private  String email;
    private Date createDate;

    /**
     * Constructor form.
     * @param id Id.
     * @param name Name.
     * @param login Login.
     * @param email email.
     */
    public Users(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Users{" + "name='" + name + '\'' + ", login='" + login + '\'' + ", email='" + email + '\'' + '}';
    }
}
