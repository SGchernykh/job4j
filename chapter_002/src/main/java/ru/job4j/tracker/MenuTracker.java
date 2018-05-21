package ru.job4j.tracker;

import java.util.ArrayList;

public class MenuTracker {
    private  Input input;
    private  Tracker tracker;

    private ArrayList<UserAction>  action = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.action.add(0, new AddItem(0, "Add the new item"));
        this.action.add(1, new AllItem(1, "Show all items."));
        this.action.add(2, new EditItem(2, "Edit item."));
        this.action.add(3, new DeleteItem(3, "Delete item."));
        this.action.add(4, new FindById(4, "Find item by Id."));
        this.action.add(5, new FindByName(5, "Find items by name."));
        this.action.add(6, new Exit(6, "Exit."));
    }

    public ArrayList<Integer> rangeArray() {
        ArrayList<Integer> range = new ArrayList<>();
        for (int index = 0; index < this.action.size(); index++) {
            range.add(index, index);
        }
        return range;
    }

    public void select(int key) {
        this.action.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.action) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private static void marginsItems(Item item) {
        System.out.printf("ID заявки:%s |Имя заявки:%s |Описание:%s |Время создания заявки:%s |Коментарий к заявки:%s%n",
                item.getId(),
                item.getName(),
                item.getDesc(),
                item.getCreated(),
                item.getComments());
    }

    private static class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the taks's name:");
            String desc = input.ask("Please, enter the taks's desc:");
            tracker.add(new Item(name, desc));
        }
    }

    private static class AllItem extends BaseAction {
        public AllItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Ваши заявки --------------");
            for (Item item : tracker.getAll()) {
                marginsItems(item);
            }
        }
    }

    private static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование  заявки --------------");
            String id = input.ask("Введите ID заявки: ");
            String name = input.ask("Введите новое имя заявки: ");
            String desc = input.ask("Введите новое описание: ");
            Item item = new Item(name, desc);
            tracker.replace(id, item);
            System.out.println("------ Заявка с getId: " + item.getId() + " обновлена-----------");
        }
    }

    private static class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление  заявки --------------");
            String id = input.ask("Введите ID заявки : ");
            tracker.delete(id);
            System.out.println("------------ Заявка удалена --------------");
        }
    }

    private static class FindById extends BaseAction {
        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = input.ask("Введите ID заявки: ");
            marginsItems(tracker.findById(id));
        }
    }

    private static class FindByName extends BaseAction {
        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по Name --------------");
            String name = input.ask("Введите name заявки: ");
            for (Item item : tracker.findByName(name)) {
                if (item != null) {
                    marginsItems(item);
                }
            }
        }
    }

    private static class Exit extends BaseAction {
        public Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }

}
