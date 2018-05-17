package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String ALL = "1";
    /**
     * Константа меню для изменения заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FIND_ID = "4";
    /**
     * Константа меню для поиска заявки по Имени.
     */
    private static final String FIND_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            switch (answer) {
                case ADD : this.createItem(); break;
                case ALL : this.allItem(); break;
                case EDIT : this.editItem(); break;
                case DELETE : this.deleteItem(); break;
                case FIND_ID : this.findById(); break;
                case FIND_NAME : this.findByName(); break;
                case EXIT : exit = true; break;
                default: break;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId: " + item.getId() + "-----------");
    }
    /**
     * Метод реализует отображения всех заявок в хранилище.
     */
    private void allItem() {
        System.out.println("------------ Ваши заявки --------------");
        for (Item item : tracker.getAll()) {
            System.out.print("--ID заявки: " + item.getId());
            System.out.print(" ---Имя заявки: " + item.getName());
            System.out.print(" ---Описание: " + item.getDesc());
            System.out.print(" ---Время создания заявки: " + item.getCreated());
            System.out.println(" --Коментарий к заявки: " + item.getComments());
        }
    }
    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование  заявки --------------");
        String id = this.input.ask("Введите ID заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String desc = this.input.ask("Введите новое описание: ");
        Item item = new Item(name, desc);
        tracker.replace(id, item);
        System.out.println("------ Заявка с getId: " + item.getId() + " обновлена-----------");
    }
    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление  заявки --------------");
        String id = this.input.ask("Введите ID заявки : ");
        tracker.delete(id);
        System.out.println("------------ Заявка удалена --------------");
    }
    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findById() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = this.input.ask("Введите ID заявки: ");
        System.out.print(" --ID заявки: " + tracker.findById(id).getId());
        System.out.print(" --Имя заявки: " + tracker.findById(id).getName());
        System.out.print(" --Описание: " + tracker.findById(id).getDesc());
        System.out.print(" --Время создания заявки: " + tracker.findById(id).getCreated());
        System.out.println(" --Коментарий к заявки: " + tracker.findById(id).getComments());

    }
    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findByName() {
        System.out.println("------------ Поиск заявки по Name --------------");
        String name = this.input.ask("Введите name заявки: ");
        for (Item item : tracker.findByName(name)) {
            if (item != null) {
                System.out.print("--ID заявки: " + item.getId());
                System.out.print(" ---Имя заявки: " + item.getName());
                System.out.print(" ---Описание: " + item.getDesc());
                System.out.print(" ---Время создания заявки: " + item.getCreated());
                System.out.println(" ---Коментарий к заявки: " + item.getComments());
            }
        }
    }
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit item.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by Id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit Program.");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}