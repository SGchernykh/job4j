package ru.job4j.htmlcssjs.model;
/**
 * Item.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private final String name;
    private final String surname;
    private final String sex;
    private final String description;

    /**
     * Constructor
     * @param name
     * @param surname
     * @param sex
     * @param description
     */
    public Item(final String name, final String surname, final String sex, final String description) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Item [name=%s, surname=%s, sex=%s, description=%s]", this.name, this.surname, this.sex, this.description);
    }
}
