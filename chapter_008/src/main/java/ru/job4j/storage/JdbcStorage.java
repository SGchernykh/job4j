package ru.job4j.storage;

import org.springframework.stereotype.Component;
import ru.job4j.model.User;

@Component
public class JdbcStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println("jdbc store");
    }
}
