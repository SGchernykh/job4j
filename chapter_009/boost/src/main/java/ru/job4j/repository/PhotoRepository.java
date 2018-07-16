package ru.job4j.repository;

/**
 * Photo CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}