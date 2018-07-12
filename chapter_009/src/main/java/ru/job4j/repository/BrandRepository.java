package ru.job4j.repository;

/**
 * Brand CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.components.Brand;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Brand findByName(final String name);
}