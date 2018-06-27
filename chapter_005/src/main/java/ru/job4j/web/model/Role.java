package ru.job4j.web.model;

/**
 * Role.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Role {
    private int id;
    private String role;

    /**
     * Constructor.
     * @param id
     * @param role
     */
    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

}
