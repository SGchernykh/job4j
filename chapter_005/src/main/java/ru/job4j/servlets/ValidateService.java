package ru.job4j.servlets;

/**
 * ValidateService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.List;

public class ValidateService implements Validate {
    private final Store logic = MemoryStore.getInstance();
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
    public void add(String name, String login, String email) {
        boolean result = true;
        for (Users user : this.logic.findAll()) {
            if ((email.equals(user.getEmail())) && login.equals(user.getLogin())) {
                result = false;
                break;
            }
        }
        if (result) {
            this.logic.add(name, login, email);
        }
    }

    @Override
    public void update(int id, String name, String login, String email) {
        if (id < this.logic.findAll().size() && id >= 0) {
            this.logic.update(id, name, login, email);
        }
    }

    @Override
    public void delete(int id) {
        if (id < this.logic.findAll().size() && id >= 0) {
            this.logic.delete(id);
        }
    }

    @Override
    public List<Users> findAll() {
        return this.logic.findAll();
    }

    @Override
    public Users findById(int id) {
        Users user = null;
        if (id < this.logic.findAll().size() && id >= 0) {
            user = this.logic.findById(id);
        }
        return user;
    }
}
