package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.CarBody;

@Repository
public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {

  CarBody findById(final int id);
}
