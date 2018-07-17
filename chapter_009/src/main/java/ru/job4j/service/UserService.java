package ru.job4j.service;

/**
 * UserService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.repository.UserRepository;

@Service
@Transactional
public class UserService {
    private BCryptPasswordEncoder encoder;
    private SecurityService securityService;
    private UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, SecurityService securityService, UserRepository userRepository) {
        this.encoder = encoder;
        this.securityService = securityService;
        this.userRepository = userRepository;
    }


    /**
     * Save User in storage.
     * @param value User.
     * @return User.
     */
    @Transactional
    public User save(final User value) {
        return this.userRepository.save(value);
    }

    /**
     * Get user by id from storage.
     * @param id id.
     * @return user.
     */
    @Transactional(readOnly = true)
    public User getById(final int id) {
        return this.userRepository.findById(id).get();
    }

    /**
     * Get user bu name from storage.
     * @param login login.
     * @return user.
     */
    @Transactional(readOnly = true)
    public User getByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void regUser(final User user) {
        String pass = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(new Role(1));
        this.save(user);
        securityService.autoLogin(user.getLogin(), pass);
    }
}
