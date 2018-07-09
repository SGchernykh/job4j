package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.model.User;

@Component
public class UserStorage implements Storage {

    private final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        this.storage.add(user);
    }

}
