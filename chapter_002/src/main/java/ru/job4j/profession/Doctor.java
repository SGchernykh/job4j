package ru.job4j.profession;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {


    public Doctor(String name, String profession) {
        this.setName(name);
        this.setProfesson(profession);
    }

    public Diagnose heal(Pacient pacient) {
        return  new Diagnose();
    }
}
