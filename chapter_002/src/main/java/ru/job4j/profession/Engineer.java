package ru.job4j.profession;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
    public Engineer(String name, String profession) {
        this.setName(name);
        this.setProfesson(profession);
    }
    public Village buildHouse(House house) {
        return new Village();
    }
}
