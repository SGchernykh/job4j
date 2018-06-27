package ru.job4j.web.store;

/**
 * ValidateService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.Users;

import java.util.List;

public class ValidateService implements Validate {
    private final Store logic = DBStore.getInstance();
    private static final Validate INSTANCE = new ValidateService();

    /**
     * Constructor.
     */
    private ValidateService() {
    }

    /**
     * Pattern Singleton
     * @return MemoryStore.
     */
    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Users users) {
        boolean result = true;
        for (Users user : this.logic.findAll()) {
            if ((users.getEmail().equals(user.getEmail())) && users.getLogin().equals(user.getLogin())) {
                result = false;
                break;
            }
        }
        if (result) {
            this.logic.add(users);
        }
        return result;
    }

    @Override
    public boolean update(Users users) {
        this.logic.update(users);
        return true;
    }

    @Override
    public boolean delete(int id) {
        this.logic.delete(id);
        return true;
    }

    @Override
    public List<Users> findAll() {
        return this.logic.findAll();
    }

    @Override
    public Users findById(int id) {
        return this.logic.findById(id);
    }
}
