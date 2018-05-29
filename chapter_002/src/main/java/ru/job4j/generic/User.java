package ru.job4j.generic;

/**
 * Class User
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User extends Base {

    /**
     * Constructor.
     * @param id values.
     */
    protected User(String id) {
        super(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}