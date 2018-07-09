package ru.job4j.storage;

/**
 * JdbcStorage.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.job4j.model.User;

import java.sql.PreparedStatement;

@Component
public class JdbcStorage implements Storage<User> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcStorage(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(User user) {
        final String query = "insert into users (name, login, password) values (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement statement = con.prepareStatement(query, new String[]{"id"});
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            return statement;
        }, keyHolder);
        user.setId((Integer) keyHolder.getKey());
    }

    @Override
    public User getById(int id) {
        final String query = "select * from users where id = ?";
        User returnUser = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);
        return returnUser;
    }
}
