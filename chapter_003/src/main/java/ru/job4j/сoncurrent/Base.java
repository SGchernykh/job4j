package ru.job4j.сoncurrent;

import java.util.Objects;

public class Base {
    private int id;
    private String name = "";
    private int version;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
