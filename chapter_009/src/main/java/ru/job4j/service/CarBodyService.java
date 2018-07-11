package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.CarBody;
import ru.job4j.repository.CarBodyRepository;

import java.util.List;

@Service
public class CarBodyService {

    @Autowired
    private CarBodyRepository carBodyRepository;

    public List<CarBody> getAll() {
        return (List<CarBody>) this.carBodyRepository.findAll();
    }

    public CarBody getById(final int id) {
        return this.carBodyRepository.findById(id);
    }
}
