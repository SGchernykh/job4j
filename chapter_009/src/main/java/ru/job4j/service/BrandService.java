package ru.job4j.service;

/**
 * BrandService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.Brand;
import ru.job4j.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Get All Brand.
     * @return List Brands.
     */
    public List<Brand> getAll() {
        return (List<Brand>) this.brandRepository.findAll();
    }
}