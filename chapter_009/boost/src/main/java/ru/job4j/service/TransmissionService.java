package ru.job4j.service;

/**
 * TransmissionService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.components.Transmission;
import ru.job4j.repository.TransmissionRepository;

import java.util.List;

@Service
public class TransmissionService {
    private TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    /**
     * Get All Transmission from storage.
     * @return List Transmission.
     */
    public List<Transmission> getAll() {
        return (List<Transmission>) this.transmissionRepository.findAll();
    }

    /**
     * Get Transmission by id from storage.
     * @param id Id.
     * @return Transmission.
     */
    public Transmission getById(final int id) {
        return this.transmissionRepository.findById(id);
    }
}