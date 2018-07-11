package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.Photo;
import ru.job4j.repository.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo save(final Photo value) {
        return this.photoRepository.save(value);
    }

    public Photo getById(final int id) {
        return this.photoRepository.findById(id).get();
    }
}
