package ru.job4j.generic;

/**
 * Class User Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStore<User extends Base> extends AbstractStore<User> {
    /**
     * Constructor.
     * @param size Size array.
     */
    public UserStore(int size) {
        super(size);
    }
}