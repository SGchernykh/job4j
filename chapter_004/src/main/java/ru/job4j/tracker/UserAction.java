package ru.job4j.tracker;
/**
 * UserAction.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();

}
