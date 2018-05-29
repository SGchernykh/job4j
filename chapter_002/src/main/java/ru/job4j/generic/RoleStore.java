package ru.job4j.generic;

/**
 * Class Role Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RoleStore<Role extends Base> extends AbstractStore<Role> {

    /**
     * Constructor.
     * @param size Size array.
     */
    public RoleStore(int size) {
        super(size);
    }
}