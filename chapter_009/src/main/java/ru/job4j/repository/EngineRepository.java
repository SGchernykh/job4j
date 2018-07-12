package ru.job4j.repository;

/**
 * Engine CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.Engine;

@Repository
public interface EngineRepository extends CrudRepository<Engine, Integer> {

    Engine findById(final int id);
}