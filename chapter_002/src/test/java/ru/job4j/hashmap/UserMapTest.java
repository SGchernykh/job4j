package ru.job4j.hashmap;

/**
 * User Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Calendar;

public class UserMapTest {
    UserMap map = new UserMap();

    @Test
    public void mapLesson2() {
        User user1 = new User("test", 2, Calendar.getInstance());
        User user2 = new User("test", 2, Calendar.getInstance());
        map.add(user1, 1);
        map.add(user2, 2);
        map.displayMap();
    }
}