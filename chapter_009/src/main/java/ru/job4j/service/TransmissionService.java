package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.Transmission;
import ru.job4j.repository.TransmissionRepository;

import java.util.List;

@Service
public class TransmissionService {

    @Autowired
    private TransmissionRepository transmissionRepository;

    public List<Transmission> getAll() {
        return (List<Transmission>) this.transmissionRepository.findAll();
    }

    public Transmission getById(final int id) {
        return this.transmissionRepository.findById(id);
    }
}
