package ru.job4j.generic;

/**
 * Abstract class Store with common methods.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> array;

    /**
     * Constructor.
     * @param size Size array.
     */
    public AbstractStore(int size) {
        this.array = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        this.array.set(Integer.parseInt(id), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
       this.array.delete(Integer.parseInt(id));
        return true;
    }

    @Override
    public T findById(String id) {
        return this.array.get(Integer.parseInt(id));
    }
}