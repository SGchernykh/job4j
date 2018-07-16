package ru.job4j.domain.components;

/**
 * Transmission.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;

@Entity
@Table(name = "transmission")
public class Transmission extends ComponentsCar {


    public Transmission() {
    }

    public Transmission(int id) {
        setId(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId() {
        return super.getId();
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}