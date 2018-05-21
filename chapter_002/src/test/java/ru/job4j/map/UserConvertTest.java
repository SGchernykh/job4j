package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test Converts List User in Map Users.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    @Test
    public void whenConvrtingListUserInMapUsers() {
        UserConvert list = new UserConvert();
        List<User> input = new ArrayList<>();
        HashMap<Integer, User> expect = new HashMap<>();
        HashMap<Integer, User> result;
        User user1 = new User(1, "test1", "ekb1");
        User user2 = new User(2, "test2", "ekb2");
        User user3 = new User(3, "test3", "ekb3");
        input.add(user1);
        input.add(user2);
        input.add(user3);
        expect.put(user1.getId(), user1);
        expect.put(user2.getId(), user2);
        expect.put(user3.getId(), user3);
        result = list.process(input);
        assertThat(result, is(expect));
    }
}
