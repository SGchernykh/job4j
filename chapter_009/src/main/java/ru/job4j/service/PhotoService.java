package ru.job4j.service;

/**
 * PhotoService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.Photo;
import ru.job4j.repository.PhotoRepository;

@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional
    public Photo save(final Photo value) {
        return this.photoRepository.save(value);
    }

    /**
     * Get Photo by id from storage.
     * @param id Id.
     * @return Photo.
     */
    @Transactional(readOnly = true)
    public Photo getById(final int id) {
        return this.photoRepository.findById(id).get();
    }
}