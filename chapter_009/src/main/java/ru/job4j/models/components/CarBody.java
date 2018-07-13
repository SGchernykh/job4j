package ru.job4j.models.components;
/**
 * CarBody.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;

@Entity
@Table(name = "car_body")
public class CarBody extends ComponentsCar {

    public CarBody() {
    }

    public CarBody(final int id) {
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