package ru.job4j.storage;

/**
 * Storage.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Storage<T> {
    /**
     * Add in store.
     * @param entity
     */
    void add(final T entity);

    /**
     * GetById.
     * @param id Id.
     * @return Element type T.
     */
    T getById(int id);
}
