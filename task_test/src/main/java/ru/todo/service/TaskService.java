package ru.todo.service;

/**
 * TaskService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.todo.domain.Task;
import ru.todo.repository.TaskRepository;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTask() {
        return this.taskRepository.getAllTask();
    }

    @Transactional(readOnly = true)
    public Task getById(int id) {
        return this.taskRepository.getById(id);
    }

    public boolean add(Task task) {
       return this.taskRepository.add(task);
    }

    public boolean edit(Task task) {
        return this.taskRepository.edit(task);
    }
}