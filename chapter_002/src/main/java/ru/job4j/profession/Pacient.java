package ru.job4j.profession;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Pacient {
    private String name;
    private String pains;

    public void setName(String name) {
        this.name = name;
    }

    public void setPains(String pains) {
        this.pains = pains;
    }

    public String getName() {
        return name;
    }

    public String getPains() {
        return pains;
    }
}
