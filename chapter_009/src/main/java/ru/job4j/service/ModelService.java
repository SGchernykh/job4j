package ru.job4j.service;

/**
 * ModelService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.Model;
import ru.job4j.repository.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    /**
     * Get Model by id from storage.
     * @param id Id.
     * @return Model.
     */
    public Model getById(final int id) {
        return this.modelRepository.findById(id);
    }

    /**
     * Get All Model with brand_id from storage.
     * @return List Model.
     */
    public List<Model> getModelByBrandId(final int id) {
        return this.modelRepository.findByBrandId(id);
    }
}