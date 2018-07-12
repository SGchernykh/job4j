package ru.job4j.repository;

/**
 * City CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    City findById(final int id);
}