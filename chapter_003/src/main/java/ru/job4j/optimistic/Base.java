package ru.job4j.optimistic;

/**
 * Base
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Objects;

public class Base {
    private int id;
    private String name = "";
    private int version;

    /**
     * Constructor.
     * @param id Id.
     * @param name Name.
     */
    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    /**
     * Update version.
     */
    public void updateVersion() {
        this.version++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}