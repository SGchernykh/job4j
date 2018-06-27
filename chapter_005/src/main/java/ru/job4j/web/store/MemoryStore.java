package ru.job4j.web.store;

/**
 * MemoryStore.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.Role;
import ru.job4j.web.model.Users;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {
    private int count = 0;
    private final List<Users> base = new CopyOnWriteArrayList<>();
    private static final Store INSTANCE = new MemoryStore();

    /**
     * Constructor.
     */
    private MemoryStore() {
    }

    @Override
    public void add(Users users) {
    }

    @Override
    public void update(Users users) {
    }

    @Override
    public void delete(int id) {
        this.base.remove(id);
        count--;
    }

    @Override
    public List<Users> findAll() {
        return this.base;
    }

    @Override
    public Users findById(int id) {
        return base.get(id);
    }

    /**
     * Pattern Singleton
     * @return Base
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Users getUserByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public List<Role> roleAll() {
        return null;
    }
}
