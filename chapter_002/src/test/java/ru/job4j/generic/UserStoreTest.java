package ru.job4j.generic;

/**
 * Class User Store Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class UserStoreTest {
    private UserStore<User> store = new UserStore<>(10);

    @Before
    public void addUser() {
        User user = new User("157");
        this.store.add(user);
    }

    @Test
    public void whenAddUser() {
        User result = store.findById("0");
        assertThat(result.getId(), is("157"));
    }

    @Test
    public void whenReplaceUser() {
        User user = new User("2017");
        store.replace("0", user);
        User result = store.findById("0");
        assertThat(result.getId(), is("2017"));
    }

    @Test
    public void whenDeleteUser() {
        store.delete("0");
        assertNull(store.findById("0"));
    }

    @Test
    public void whenFindByIdeUser() {
        User result = store.findById("0");
        assertThat(result.getId(), is("157"));
    }
}