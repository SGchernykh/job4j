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
        this.action.add(new AddItem(0, "Add the new item"));
        this.action.add(new AllItem(1, "Show all items."));
        this.action.add(new EditItem(2, "Edit item."));
        this.action.add(new DeleteItem(3, "Delete item."));
        this.action.add(new FindById(4, "Find item by Id."));
        this.action.add(new FindByName(5, "Find items by name."));
        this.action.add(new Exit(6, "Exit."));
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

    private static class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final String name = input.ask("Enter the name new task: ");
            final String description = input.ask("Enter the description new item: ");
            final Item item = new Item(0, name, description, System.currentTimeMillis());
            tracker.addItem(item);
        }
    }

    private static class AllItem extends BaseAction {
        public AllItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final StringBuilder sb = new StringBuilder();
            final ArrayList<Item> items = tracker.getAll();
            for (Item item : items) {
                sb.append(item).append(System.getProperty("line.separator"));
            }
            System.out.println(sb.toString());
        }
    }

    private static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final int id = Integer.parseInt(input.ask("Enter the item ID that you want to edit: "));
            final String name = input.ask("Enter new task name: ");
            final String description = input.ask("Enter new desc: ");
            final Item item = new Item(id, name, description, 0);
            tracker.replace(item);
        }
    }

    private static class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final int id = Integer.parseInt(input.ask("Enter the ID for the task you want to delete: "));
            tracker.delete(id);
        }
    }

    private static class FindById extends BaseAction {
        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final StringBuilder sb = new StringBuilder();
            final String id = input.ask("Enter id: ");
            final Item item = tracker.findById(Integer.parseInt(id));
            sb.append(item).append(System.getProperty("line.separator"));
            System.out.println(sb.toString());
        }
    }

    private static class FindByName extends BaseAction {
        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            final StringBuilder sb = new StringBuilder();
            final String name = input.ask("Enter name: ");
            final ArrayList<Item> items = tracker.findByName(name);
            for (Item item : items) {
                sb.append(item).append(System.getProperty("line.separator"));
            }
            System.out.println(sb.toString());
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
