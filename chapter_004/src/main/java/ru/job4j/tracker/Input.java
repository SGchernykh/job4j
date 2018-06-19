package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    String ask(String question);

    int ask(String question, ArrayList<Integer> range);
}
