package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.DriveUnit;

@Repository
public interface DriveUnitRepository extends CrudRepository<DriveUnit, Integer> {

    DriveUnit findById(final int id);
}