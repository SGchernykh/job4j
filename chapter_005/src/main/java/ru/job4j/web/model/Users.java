package ru.job4j.web.model;

/**
 * Users.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */


import java.sql.Timestamp;
import java.util.Date;

public class Users {
    private final int id;
    private  String name;
    private  String login;
    private String password;
    private  String email;
    private Timestamp createDate;
    private Role role;

    /**
     * Constructor form.
     * @param id Id.
     * @param name Name.
     * @param login Login.
     * @param email email.
     */
    public Users(int id, String name, String login, String password, String email, Timestamp createDate, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public Users(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Users(String name, String login, String password, String email, Timestamp createDate, Role role) {
        this.id = 0;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
