package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.Brand;
import ru.job4j.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll() {
        return (List<Brand>) this.brandRepository.findAll();
    }

    public Brand getByName(final String name) {
        return this.brandRepository.findByName(name);
    }
}
