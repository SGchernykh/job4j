package ru.job4j.tracker;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ArrayList;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList<>();
    private static final Random RM = new Random();
    private  int count = 1;

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище.
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * Метод реализаущий редактирование заявок.
     * @param item новая заявка
     * @param id идентификатор заявки.
     */
    public void replace(String id, Item item) {

        for (int index = 0; index < this.position; index++) {
            if (item != null && this.items.get(index).getId().equals(id)) {
                this.items.add(index, item);
                item.setId(id);
            }
        }
    }

    /**
     * Метод реализаущий удаление заявки из хранилища.
     * @param id идентификатор заявки.
     */
    public void delete(String id) {
        for (int index = 0; index != this.position; index++) {
            if (this.items.get(index) != null && this.items.get(index).getId().equals(id)) {
                this.items.remove(index);
                this.position--;
                break;
            }
        }
    }

    /**
     * Метод реализаущий получение списка всех заявок.
     */
    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        for (int index = 0; index != this.position; index++) {
            result.add(index, this.items.get(index));
        }
        return result;
    }


    /**
     * Метод реализаущий получение списка по имени.
     * @param key имя
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        int count = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result.add(count, item);
                count++;
            }
        }
        return result;
    }

    /**
     * Метод реализаущий получение заявки по id.
     * @param id идентификатор заявки.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;

    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        String key = String.valueOf(RM.nextInt(100) * System.currentTimeMillis() + this.count++);
        return key;
    }
}
