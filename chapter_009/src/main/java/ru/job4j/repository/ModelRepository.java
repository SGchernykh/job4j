package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.Model;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {

    Model findById(final int id);

    List<Model> findByBrandId(final int id);
}
