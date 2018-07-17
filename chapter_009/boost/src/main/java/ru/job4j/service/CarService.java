package ru.job4j.service;

/**
 * CarService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Car;
import ru.job4j.repository.CarRepository;

@Service
@Transactional
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Save Car in storage.
     * @param value Car.
     * @return Car.
     */
    @Transactional
    public Car save(final Car value) {
        return this.carRepository.save(value);
    }
}