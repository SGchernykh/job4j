package ru.job4j.models.components;
/**
 * DriveUnit.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;

@Entity
@Table(name = "drive_unit")
public class DriveUnit extends ComponentsCar {

    @Id
    @GeneratedValue
    @Override
    public int getId() {
        return super.getId();
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

}