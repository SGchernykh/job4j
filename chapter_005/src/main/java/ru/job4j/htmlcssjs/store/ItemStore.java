package ru.job4j.htmlcssjs.store;


import ru.job4j.htmlcssjs.model.Item;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ItemStore {
    private int count = 0;
    private final List<Item> base = new CopyOnWriteArrayList<>();
    private static final ItemStore INSTANCE = new ItemStore();

    /**
     * Constructor.
     */
    private ItemStore() {
    }
    public void add(Item item) {
        this.base.add(item);
        this.count++;
    }

    public List<Item> getPoll() {
       return this.base;
    }

    public static ItemStore getInstance() {
        return INSTANCE;
    }
}
