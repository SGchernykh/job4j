package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test User sorted.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    private ArrayList<User> list = new ArrayList<>();
    @Before
    public void userList() {
        User user1 = new User("test1", 20);
        User user2 = new User("test2222", 19);
        User user3 = new User("test3", 25);
        User user4 = new User("test4", 17);
        User user5 = new User("test44", 14);
        User user6 = new User("test4", 35);
        this.list.add(user1);
        this.list.add(user2);
        this.list.add(user3);
        this.list.add(user4);
        this.list.add(user5);
        this.list.add(user6);

    }
    @Test
    public void whenListUsersSortedSet() {
        SortUser sortUser = new SortUser();
        Set<User> result;
        ArrayList<User> expect = new ArrayList<>();
        User user1 = new User("test1", 20);
        User user2 = new User("test2222", 19);
        User user3 = new User("test3", 25);
        User user4 = new User("test4", 17);
        User user5 = new User("test44", 14);
        User user6 = new User("test4", 35);
        expect.add(user5);
        expect.add(user4);
        expect.add(user2);
        expect.add(user1);
        expect.add(user3);
        expect.add(user6);
        result = sortUser.sort(this.list);
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void sortTheListByWordLength() {
        SortUser sortUser = new SortUser();
        List<User> result;
        ArrayList<User> expect = new ArrayList<>();
        User user1 = new User("test1", 20);
        User user2 = new User("test2222", 19);
        User user3 = new User("test3", 25);
        User user4 = new User("test4", 17);
        User user5 = new User("test44", 14);
        User user6 = new User("test4", 35);
        expect.add(user1);
        expect.add(user3);
        expect.add(user4);
        expect.add(user6);
        expect.add(user5);
        expect.add(user2);
        result = sortUser.sortNameLength(this.list);
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void whenSortedListByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> result;
        ArrayList<User> expect = new ArrayList<>();
        User user1 = new User("test1", 20);
        User user2 = new User("test2222", 19);
        User user3 = new User("test3", 25);
        User user4 = new User("test4", 17);
        User user5 = new User("test44", 14);
        User user6 = new User("test4", 35);
        expect.add(user1);
        expect.add(user2);
        expect.add(user3);
        expect.add(user4);
        expect.add(user6);
        expect.add(user5);
        result = sortUser.sortByAllFields(this.list);
        assertThat(result.toString(), is(expect.toString()));
    }

}
