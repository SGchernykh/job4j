package ru.job4j.tracker;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemFromTracker() {
        Tracker tracker = new Tracker();
        Item items = new Item("test4", "testDescription2", 232L);
        tracker.add(new Item("test1", "testDescription", 123L));
        tracker.add(new Item("test2", "testDescription2", 1234L));
        tracker.add(new Item("test2", "testDescription2", 1232L));
        tracker.add(items);
        tracker.delete(items.getId());
        assertThat(tracker.getAll().length, is(3));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        int count = 0;
        tracker.add(new Item("test4", "testDescription2", 232L));
        tracker.add(new Item("test1", "testDescription", 123L));
        tracker.add(new Item("test2", "testDescription2", 1234L));
        tracker.add(new Item("test2", "testDescription2", 1235L));
        tracker.add(new Item("test2", "testDescription2", 1232L));
        for (Item items : tracker.findByName("test2")) {
            if (items != null) {
                count++;
            }
        }
        assertThat(count, is(3));
    }

    @Test
    public void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item items = new Item("test1", "testDescription", 123L);
        tracker.add(new Item("test2", "testDescription2", 1234L));
        tracker.add(new Item("test2", "testDescription2", 1232L));
        tracker.add(items);
        assertThat(tracker.findById(items.getId()).getName(), is("test1"));
    }

    @Test
    public void whenFindAllItems() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test4", "testDescription2", 232L));
        tracker.add(new Item("test1", "testDescription", 123L));
        tracker.add(new Item("test2", "testDescription2", 1234L));
        tracker.add(new Item("test2", "testDescription2", 1232L));
        assertThat(tracker.getAll().length, is(4));
    }
}
