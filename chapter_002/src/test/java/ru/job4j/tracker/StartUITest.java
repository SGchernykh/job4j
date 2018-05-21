package ru.job4j.tracker;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Tracker tracker;

    private StringBuilder menuList() {
        StringBuilder menu = new StringBuilder()
                .append("0. Add the new item")
                .append(System.lineSeparator())
                .append("1. Show all items.")
                .append(System.lineSeparator())
                .append("2. Edit item.")
                .append(System.lineSeparator())
                .append("3. Delete item.")
                .append(System.lineSeparator())
                .append("4. Find item by Id.")
                .append(System.lineSeparator())
                .append("5. Find items by name.")
                .append(System.lineSeparator())
                .append("6. Exit.")
                .append(System.lineSeparator());
        return menu;
    }

    private StringBuilder marginsItems(Item items) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("ID заявки:" + items.getId())
                .append(" |Имя заявки:" + items.getName())
                .append(" |Описание:" + items.getDesc())
                .append(" |Время создания заявки:" + items.getCreated())
                .append(" |Коментарий к заявки:" + items.getComments())
                .append(System.lineSeparator());
        return stringBuilder;
    }

    @Before
    public void createTracer() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
        this.tracker = new Tracker();
        this.tracker.add(new Item("test1", "testDescription1", 123L));
        this.tracker.add(new Item("test0", "testDescription2", 123L));
        this.tracker.add(new Item("test3", "testDescription3", 123L));
        this.tracker.add(new Item("test1", "testDescription4", 123L));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("0");
        list.add("test name");
        list.add("desc");
        list.add("y");
        Input input = new StubInput(list);
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findByName("test name").get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Item item = this.tracker.add(new Item("test1", "testDescription", 123L));
        ArrayList<String> list = new ArrayList<>();
        list.add("2");
        list.add(item.getId());
        list.add("test name");
        list.add("desc");
        list.add("y");
        Input input = new StubInput(list);
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteItemThenTrackerDeleteValue() {
        Item item = this.tracker.add(new Item("test1", "testDescription", 123L));
        ArrayList<String> list = new ArrayList<>();
        list.add("3");
        list.add(item.getId());
        list.add("y");
        Input input = new StubInput(list);
        new StartUI(input, this.tracker).init();
        assertThat((this.tracker.getAll().size()), is(4));
    }

    @Test
    public void whenFindItemByNameThenTrackerDisplayValue() {
        Item items = this.tracker.add(new Item("test2", "testDescription", 123L));
        Item item = this.tracker.add(new Item("test2", "testDescription", 123L));
        ArrayList<String> list = new ArrayList<>();
        list.add("5");
        list.add("test2");
        list.add("y");
        Input input = new StubInput(list);
        new StartUI(input, this.tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menuList())
                                .append("------------ Поиск заявки по Name --------------")
                                .append(System.lineSeparator())
                                .append(this.marginsItems(items))
                                .append(this.marginsItems(item))
                                .toString()
                )
        );
    }
    @Test
    public void whenFindAllItemThenTrackerDisplayValue() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("y");
        Input input = new StubInput(list);
        new StartUI(input, this.tracker).init();
        for (Item value : this.tracker.getAll()) {
            stringBuilder.append(this.marginsItems(value));
        }
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menuList())
                                .append("------------ Ваши заявки --------------")
                                .append(System.lineSeparator())
                                .append(stringBuilder)
                                .toString()
                )
        );
    }
}
