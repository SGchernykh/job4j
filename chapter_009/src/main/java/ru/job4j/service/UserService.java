package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.User;
import ru.job4j.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(final User value) {
        return this.userRepository.save(value);
    }

    /**
     * Get user by id from storage.
     *
     * @param id id.
     * @return user.
     */
    public User getById(final int id) {
        return this.userRepository.findById(id).get();
    }

    /**
     * Get user bu name from storage.
     *
     * @param name name.
     * @return user.
     */
    public User getById(final String name) {
        return userRepository.findByName(name);
    }
}
