package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    City findById(final int id);
}
