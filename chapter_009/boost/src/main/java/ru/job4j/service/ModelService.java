package ru.job4j.service;

/**
 * ModelService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.components.Model;
import ru.job4j.repository.ModelRepository;

import java.util.List;

@Service
@Transactional
public class ModelService {
    private ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    /**
     * Get Model by id from storage.
     * @param id Id.
     * @return Model.
     */
    @Transactional(readOnly = true)
    public Model getById(final int id) {
        return this.modelRepository.findById(id);
    }

    /**
     * Get All Model with brand_id from storage.
     * @return List Model.
     */
    @Transactional(readOnly = true)
    public List<Model> getModelByBrandId(final int id) {
        return this.modelRepository.findByBrandId(id);
    }
}