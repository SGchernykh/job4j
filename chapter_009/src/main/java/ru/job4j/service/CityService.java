package ru.job4j.service;

/**
 * CityService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.City;
import ru.job4j.repository.CityRepository;

import java.util.List;

@Service
@Transactional
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Get All City from storage.
     * @return List City.
     */
    @Transactional(readOnly = true)
    public List<City> getAll() {
        return (List<City>) this.cityRepository.findAll();
    }

    /**
     * Get City by id from storage.
     * @param id Id.
     * @return City.
     */
    @Transactional(readOnly = true)
    public City getById(final int id) {
        return this.cityRepository.findById(id);
    }
}