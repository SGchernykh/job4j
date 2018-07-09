package ru.job4j.storage;

/**
 * UserStorage.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.model.User;

@Component
public class UserStorage implements Storage<User> {

    private final Storage storage;

    @Autowired
    public UserStorage(final MemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        this.storage.add(user);
    }

    @Override
    public User getById(int id) {
        return (User) this.storage.getById(id);
    }
}
