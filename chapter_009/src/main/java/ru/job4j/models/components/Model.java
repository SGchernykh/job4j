package ru.job4j.models.components;
/**
 * ComponentsCar.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;

@Entity
@Table(name = "model")
public class Model extends ComponentsCar {

    private Brand brand;

    public Model() {
    }

    public Model(int id) {
        setId(id);
    }

    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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