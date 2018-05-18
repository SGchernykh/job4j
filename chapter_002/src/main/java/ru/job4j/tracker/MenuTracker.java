package ru.job4j.tracker;

public class MenuTracker {
    private  Input input;
    private  Tracker tracker;
    private UserAction[] action = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.action[0] = new AddItem();
        this.action[1] = new AllItem();
        this.action[2] = new EditItem();
        this.action[3] = new DeleteItem();
        this.action[4] = new FindById();
        this.action[5] = new FindByName();
        this.action[6] = new Exit();
    }
    public void select(int key) {
        this.action[key].execute(this.input, this.tracker);
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
    private static class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the taks's name:");
            String desc = input.ask("Please, enter the taks's desc:");
            tracker.add(new Item(name, desc));

        }
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item");
        }

    }

    private static class AllItem implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Ваши заявки --------------");
            for (Item item : tracker.getAll()) {
                marginsItems(item);
            }

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    private static class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item.");
        }
    }

    private static class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление  заявки --------------");
            String id = input.ask("Введите ID заявки : ");
            tracker.delete(id);
            System.out.println("------------ Заявка удалена --------------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item.");
        }
    }

    private static class FindById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = input.ask("Введите ID заявки: ");
            marginsItems(tracker.findById(id));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id.");
        }
    }

    private static class FindByName implements UserAction {
        @Override
        public int key() {
            return 5;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name.");
        }
    }

    private static class Exit implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit.");
        }
    }

}
