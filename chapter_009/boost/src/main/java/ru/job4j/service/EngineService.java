package ru.job4j.service;

/**
 * EngineService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.components.Engine;
import ru.job4j.repository.EngineRepository;

import java.util.List;

@Service
@Transactional
public class EngineService {
    private EngineRepository engineRepository;

    @Autowired
    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    /**
     * Get All Engine from storage.
     * @return List Engine.
     */
    @Transactional(readOnly = true)
    public List<Engine> getAll() {
        return (List<Engine>) this.engineRepository.findAll();
    }

    /**
     * Get Engine by id from storage.
     * @param id Id.
     * @return Engine.
     */
    @Transactional(readOnly = true)
    public Engine getById(final int id) {
        return this.engineRepository.findById(id);
    }
}