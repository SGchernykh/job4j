package ru.job4j.generic;
/**
 * Class Role Store Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {
    private RoleStore<Role> store = new RoleStore(10);

    @Before
    public void addRole() {
        Role role = new Role("157");
        this.store.add(role);
    }

    @Test
    public void whenAddRole() {
        Role result = store.findById("0");
        assertThat(result.getId(), is("157"));
    }

    @Test
    public void whenReplaceRole() {
        Role roleNew = new Role("2017");
        store.replace("0", roleNew);
        Role result = store.findById("0");
        assertThat(result.getId(), is("2017"));
    }

    @Test
    public void whenDeleteRole() {
        store.delete("0");
        assertNull(store.findById("0"));
    }

    @Test
    public void whenFindByIdeRole() {
        Role result = store.findById("0");
        assertThat(result.getId(), is("157"));
    }
}