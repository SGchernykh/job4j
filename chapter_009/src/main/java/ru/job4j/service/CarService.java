package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.Car;
import ru.job4j.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car save(final Car value) {
        return this.carRepository.save(value);
    }
}
