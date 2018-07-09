package ru.job4j.storage;

/**
 * UserStorageTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setLogin("test@mail.ru");
        user.setPassword("555");
        storage.add(user);
        assertThat(storage.getById(user.getId()), is(user));
    }
}