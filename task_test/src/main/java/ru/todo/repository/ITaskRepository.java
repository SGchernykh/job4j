package ru.todo.repository;

/**
 * ITaskRepository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.todo.domain.Task;

import java.util.List;


public interface ITaskRepository {

    /**
     * Get all list task.
     * @return a list task.
     */
    List<Task>  getAllTask();

    /**
     * Get task by id.
     * @param id id.
     * @return a task.
     */
    Task getById(int id);

    /**
     * Add task in store.
     * @param task task.
     * @return true
     */
    boolean add(Task task);

    /**
     * Edit task status.
     * @param task task.
     * @return true
     */
    boolean edit(Task task);
}
