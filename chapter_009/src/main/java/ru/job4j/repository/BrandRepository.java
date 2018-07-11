package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.components.Brand;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Brand findByName(final String name);
}
