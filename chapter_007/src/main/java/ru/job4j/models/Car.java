package ru.job4j.models;

/**
 * Car.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.models.components.*;

import javax.persistence.*;

@Entity
@Table(name = "Car")
public class Car {
    private int id;
    private Brand brand;
    private Model model;
    private CarBody carBody;
    private Transmission transmission;
    private Engine engine;
    private DriveUnit driveUnit;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "brand_id", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToOne
    @JoinColumn(name = "model_id", nullable = false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @OneToOne
    @JoinColumn(name = "car_body_id", nullable = false)
    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    @OneToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @OneToOne
    @JoinColumn(name = "engine_id", nullable = false)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @OneToOne
    @JoinColumn(name = "drive_unit_id", nullable = false)
    public DriveUnit getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(DriveUnit driveUnit) {
        this.driveUnit = driveUnit;
    }
}