package ru.job4j.todolist.store;
/**
 * Item.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.todolist.model.Item;

import java.util.List;

public interface Store {
    /**
     * Create new item.
     * @param newItem
     */
    void createItem(Item newItem);

    /**
     * Get all items.
     * @return List items.
     */
    List<Item> getAll();

    /**
     * Find item by id.
     * @param id Id.
     * @return Item with id.
     */
    Item findById(int id);

    /**
     * Update done Item
     * @param newItem
     */
    boolean update(Item newItem);
}