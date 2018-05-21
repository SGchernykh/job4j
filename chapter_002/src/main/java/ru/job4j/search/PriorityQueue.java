package ru.job4j.search;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (this.tasks.size() == 0) {
            this.tasks.add(task);
        } else {
            int size = this.tasks.size();
            for (int index = 0; index < size; index++) {
                if (task.getPriority() < this.tasks.get(index).getPriority()) {
                    this.tasks.add(index, task);
                    break;
                }
            }
        }
    }

    /**
     * The method displays and removes the first element from the list.
     * @return Displays and removes the first element.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
