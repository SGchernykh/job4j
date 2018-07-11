package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.models.components.Model;
import ru.job4j.repository.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    public List<Model> getAll() {
        return (List<Model>) this.modelRepository.findAll();
    }

    public Model getById(final int id) {
        return this.modelRepository.findById(id);
    }

    public List<Model> getModelByBrandId(final int id) {
        return this.modelRepository.findByBrandId(id);
    }
}
