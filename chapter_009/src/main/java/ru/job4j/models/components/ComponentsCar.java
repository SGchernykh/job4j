package ru.job4j.models.components;

/**
 * ComponentsCar.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class ComponentsCar {
    private int id;
    private String name;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}