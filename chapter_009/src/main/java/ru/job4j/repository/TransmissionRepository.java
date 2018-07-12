package ru.job4j.repository;

/**
 * Transmission CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.components.Transmission;

@Repository
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {

    Transmission findById(final int id);
}