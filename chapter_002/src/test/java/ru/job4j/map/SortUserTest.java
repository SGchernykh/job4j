package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test User sorted.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void whenListUsersSortedSet() {
        SortUser sortUser = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        Set<User> result;
        ArrayList<User> expect = new ArrayList<>();
        User user1 = new User("test1", 20);
        User user2 = new User("test2", 19);
        User user3 = new User("test3", 25);
        User user4 = new User("test4", 17);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        expect.add(user4);
        expect.add(user2);
        expect.add(user1);
        expect.add(user3);
        result = sortUser.sort(list);
        assertThat(result.toString(), is(expect.toString()));
    }
}
