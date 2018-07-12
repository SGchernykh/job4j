package ru.job4j.repository;

/**
 * CarBody CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.CarBody;

@Repository
public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {

  CarBody findById(final int id);
}