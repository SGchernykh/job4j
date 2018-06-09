package ru.job4j.сoncurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;


//@ThreadSafe
public class NonBlockingCache {
    //@GuardedBy("this")
    private ConcurrentHashMap<Integer, Base> baseMap = new ConcurrentHashMap<>();

    public void add(Base model) {
        this.baseMap.put(model.getId(), model);
    }

    public void update(Base model) {
        this.baseMap.computeIfPresent(model.getId(), new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer id, Base base) {
                if (base.getVersion() == model.getVersion()) {
                    base.setName(model.getName());
                    model.updateVersion();
                } else {
                    throw new OptimisticException("Versions do not match!");
                }
                return model;
            }
        });
    }

    public void delete(Base model) {
        this.baseMap.remove(model.getId());
    }

    public String get(Integer model) {
       return baseMap.get(model).getName();
    }

    public Integer size() {
        return this.baseMap.size();
    }
}
