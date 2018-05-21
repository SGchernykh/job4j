package ru.job4j.search;
/**
 * Task.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Task {
    private String desc;
    private int priority;

    /**
     * Constructor   task.
     * @param desc Description task.
     * @param priority Priority task.
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}