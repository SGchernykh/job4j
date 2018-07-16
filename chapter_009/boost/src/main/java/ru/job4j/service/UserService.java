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
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

@Service
public class UserService {
    private BCryptPasswordEncoder encoder;
    private SecurityService securityService;
    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository, RoleService roleService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    /**
     * Save User in storage.
     * @param value User.
     * @return User.
     */
    public User save(final User value) {
        return this.userRepository.save(value);
    }

    /**
     * Get user by id from storage.
     * @param id id.
     * @return user.
     */
    public User getById(final int id) {
        return this.userRepository.findById(id).get();
    }

    /**
     * Get user bu name from storage.
     * @param login login.
     * @return user.
     */
    public User getByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    public void regUser(final User user) {
        String pass = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(this.roleService.getRoleById(1));
        this.save(user);
        getSecurityService().autoLogin(user.getLogin(), pass);
    }
}
