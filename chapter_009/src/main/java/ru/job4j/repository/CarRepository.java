package ru.job4j.repository;

/**
 * Car CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

}