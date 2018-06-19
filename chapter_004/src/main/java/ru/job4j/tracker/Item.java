package ru.job4j.tracker;


import java.util.Date;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private final int id;
    private final String name;
    private final String description;
    private final Date created;
    private String[] comments;


    public Item(final int id, final String name, final String description, final long created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = new Date(created);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public String[] getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '}';
    }
}
