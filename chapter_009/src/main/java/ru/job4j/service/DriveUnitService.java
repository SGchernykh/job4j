package ru.job4j.service;

/**
 * DriveUnitService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.components.DriveUnit;
import ru.job4j.repository.DriveUnitRepository;

import java.util.List;

@Service
public class DriveUnitService {

    @Autowired
    private DriveUnitRepository driveUnitRepository;

    /**
     * Get All DriveUnit from storage.
     * @return List DriveUnit.
     */
    public List<DriveUnit> getAll() {
        return (List<DriveUnit>) this.driveUnitRepository.findAll();
    }

    /**
     * Get DriveUnit by id from storage.
     * @param id Id.
     * @return DriveUnit.
     */
    public DriveUnit getById(final int id) {
        return this.driveUnitRepository.findById(id);
    }
}