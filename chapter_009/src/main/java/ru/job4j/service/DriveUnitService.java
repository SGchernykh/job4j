package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.DriveUnit;
import ru.job4j.repository.DriveUnitRepository;

import java.util.List;

@Service
public class DriveUnitService {

    @Autowired
    private DriveUnitRepository driveUnitRepository;

    public List<DriveUnit> getAll() {
        return (List<DriveUnit>) this.driveUnitRepository.findAll();
    }

    public DriveUnit getById(final int id) {
        return this.driveUnitRepository.findById(id);
    }
}
