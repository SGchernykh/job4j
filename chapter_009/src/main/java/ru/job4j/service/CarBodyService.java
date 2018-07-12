package ru.job4j.service;

/**
 * CarBodyService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.CarBody;
import ru.job4j.repository.CarBodyRepository;

import java.util.List;

@Service
public class CarBodyService {

    @Autowired
    private CarBodyRepository carBodyRepository;

    /**
     * Get All CarBody from storage.
     * @return List CarBody.
     */
    public List<CarBody> getAll() {
        return (List<CarBody>) this.carBodyRepository.findAll();
    }

    /**
     * Get CarBody by id from storage.
     * @param id Id.
     * @return CarBody.
     */
    public CarBody getById(final int id) {
        return this.carBodyRepository.findById(id);
    }
}