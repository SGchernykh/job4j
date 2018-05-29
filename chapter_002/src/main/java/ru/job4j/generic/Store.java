package ru.job4j.generic;
/**
 * Interface Store
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends Base> {
    /**
     * Add elements.
     * @param model element type T.
     */
    void add(T model);

    /**
     * Replace element.
     * @param id Id element in array.
     * @param model element type T
     * @return True if element replace.
     */
    boolean replace(String id, T model);

    /**
     * Deleted element.
     * @param id Id element in array.
     * @return True if element deleted from array.
     */
    boolean delete(String id);

    /**
     * Find element by id.
     * @param id Id element in array.
     * @return Element with the id.
     */
    T findById(String id);
}