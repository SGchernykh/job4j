package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.Engine;
import ru.job4j.repository.EngineRepository;

import java.util.List;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public List<Engine> getAll(){
        return (List<Engine>) this.engineRepository.findAll();
    }

    public Engine findById(final int id) {
        return this.engineRepository.findById(id);
    }
}
