package ru.job4j.storage;

/**
 * MemoryStorage.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.stereotype.Component;
import ru.job4j.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryStorage implements Storage<User> {
    private final Map<Integer, User> memoryStorage = new HashMap<>();

    @Override
    public void add(User user) {
        this.memoryStorage.put(user.getId(), user);
    }

    @Override
    public User getById(final int id) {
        return this.memoryStorage.get(id);
    }

}
