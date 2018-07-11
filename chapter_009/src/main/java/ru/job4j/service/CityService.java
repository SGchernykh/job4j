package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.City;
import ru.job4j.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAll() {
        return (List<City>) this.cityRepository.findAll();
    }

    public City getById(final int id){
        return this.cityRepository.findById(id);
    }
}
