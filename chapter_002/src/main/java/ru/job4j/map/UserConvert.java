package ru.job4j.map;
/**
 * User Convert.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.HashMap;
import java.util.List;

public class UserConvert {

    /**
     * Method convert List Users  in HashMap.
     * @param list List User.
     * @return Map User.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
