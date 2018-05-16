package ru.job4j.tracker;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];
    private static final Random RM = new Random();


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
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод реализаущий редактирование заявок.
     * @param item новая заявка
     * @param id идентификатор заявки.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.position; index++)  {
            if(item != null && this.items[index].getId().equals(id)){
                this.items[index] = item;
            }
        }
    }

    /**
     * Метод реализаущий удаление заявки из хранилища.
     * @param id идентификатор заявки.
     */
    public void delete(String id) {
        for (int index = 0; index != this.position; index++) {
            if(this.items[index] != null && this.items[index].getId().equals(id)) {
                System.arraycopy(this.items,index + 1,this.items,index,this.position - index);
                this.position--;
                break;
            }
        }
    }

    /**
     * Метод реализаущий получение списка всех заявок.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
           result[index] = this.items[index];
        }
        return result;
    }


    /**
     * Метод реализаущий получение списка по имени.
     * @param key имя
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int count = 0;
        for (Item item : this.items) {
            if(item != null && item.getName().equals(key)) {
                result[count] = item;
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
            if(item != null && item.getId().equals(id)) {
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
        String key = String.valueOf(RM.nextInt(100) * System.currentTimeMillis());
        return key;
    }
}
