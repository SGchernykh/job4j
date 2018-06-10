package ru.job4j.optimistic;

/**
 * NonBlockingCache
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> baseMap = new ConcurrentHashMap<>();

    /**
     * Add.
     * @param model Element.
     */
    public void add(Base model) {
        this.baseMap.put(model.getId(), model);
    }

    /**
     * Update element.
     * @param model Element.
     */
    public void update(Base model) {
        this.baseMap.computeIfPresent(model.getId(), (id, base) -> {
            if (base.getVersion() == model.getVersion()) {
                base.setName(model.getName());
                model.updateVersion();
            } else {
                throw new OptimisticException("Versions do not match!");
            }
            return model;
        });
    }

    /**
     * Deleted element.
     * @param model Element.
     */
    public void delete(Base model) {
        this.baseMap.remove(model.getId());
    }

    /**
     * Size map.
     * @return Size.
     */
    public Integer size() {
        return this.baseMap.size();
    }
}