package ru.job4j.repository;

/**
 * Model CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.components.Model;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {

    Model findById(final int id);

    List<Model> findByBrandId(final int id);
}
