package ru.job4j.profession;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {
    public Teacher(String name, String profession) {
        this.setName(name);
        this.setProfesson(profession);
    }
    public Evaluation learmStudent(Student student) {
        return  new Evaluation();
    }
}
