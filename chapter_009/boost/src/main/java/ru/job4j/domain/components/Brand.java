package ru.job4j.domain.components;

/**
 * Brand.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand extends ComponentsCar {

    public Brand() {
    }

    public Brand(final int id) {
        setId(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}