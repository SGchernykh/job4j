package ru.job4j.service;

/**
 * BrandService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.components.Brand;
import ru.job4j.repository.BrandRepository;

import java.util.List;

@Service
@Transactional
public class BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /**
     * Get All Brand.
     * @return List Brands.
     */
    @Transactional(readOnly = true)
    public List<Brand> getAll() {
        return (List<Brand>) this.brandRepository.findAll();
    }
}