package ru.job4j.servlets;

/**
 * MemoryStore.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
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
    public void add(String name, String login, String email) {
        this.base.add(new Users(count++, name, login, email));
    }

    @Override
    public void update(int id, String name, String login, String email) {
        this.base.set(id, new Users(id, name, login, email));
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
}
