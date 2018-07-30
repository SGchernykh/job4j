package ru.todo.controller;

/**
 * ShowTaskController.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.todo.domain.ModelForFillingTask;
import ru.todo.domain.Task;
import ru.todo.lucene.index.InMemoryLuceneIndex;
import ru.todo.service.TaskService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowTaskController {
    private TaskService taskService;

    @Autowired
    public ShowTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Mapping web requests /.
     *
     * @return а page for view task.
     */
    @GetMapping(value = "/")
    public ModelAndView getTask() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    /**
     * Mapping web requests /add.
     *
     * @return а page for add task.
     */
    @GetMapping(value = "/add")
    public ModelAndView getAddTask() {
        ModelAndView view = new ModelAndView();
        view.setViewName("add");
        return view;
    }

    /**
     * Mapping web requests /list.
     *
     * @return the list of task.
     */
    @GetMapping("/list")
    public @ResponseBody
    List<Task> getAll() {
        return this.taskService.getAllTask();
    }

    /**
     * Mapping web requests /update.
     * @param id id.
     * @param radio status value.
     * @return а redirect.
     */
    @PostMapping(value = "/update")
    public String getList(@RequestParam int id, @RequestParam int radio) {
       Task task = this.taskService.getById(id);
       task = this.condition(task, radio);
       this.taskService.edit(task);
        return "redirect:/";
    }

    /**
     * Mapping web requests /edit.
     * @param id id
     * @return а page for edit task.
     */
    @GetMapping(value = "/edit")
    public ModelAndView editTask(@RequestParam int id) {
        Task task = this.taskService.getById(id);
        ModelAndView view = new ModelAndView();
        view.addObject("id", task.getId());
        view.addObject("title", task.getTitle());
        view.addObject("description", task.getDescription());
        view.setViewName("edit");
        return view;
    }

    /**
     * Mapping web requests /edit.
     * @param model model for data collection.
     * @return а redirect.
     */
    @PostMapping(value = "/edit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String editTaskBD( ModelForFillingTask model) {
        Task task =  this.taskService.getById(model.getId());
        task = this.condition(task, model.getRadio());
        task.setDescription(model.getDescription());
        task.setTitle(model.getTitle());
        this.taskService.edit(task);
        return "redirect:/";
    }

    /**
     * Mapping web requests /create.
     * @param task New task.
     * @return а redirect.
     */
    @PostMapping(value = "/create")
    public String addTask(Task task) {
        this.taskService.add(task);
        return "redirect:/";
    }

    /**
     * Mapping web requests /find.
     *
     * @return а page for find task.
     */
    @GetMapping(value = "/find")
    public ModelAndView getFindLucene() {
        ModelAndView view = new ModelAndView();
        view.setViewName("find");
        return view;
    }

    /**
     * Mapping web requests /desc.
     * @param text Text for find.
     * @return the list of description.
     */
    @GetMapping(value = "/desc")
    public @ResponseBody
    List<String> findDescription(@RequestParam String text) {
        List<String> result = new ArrayList<>();
        InMemoryLuceneIndex inMemoryLuceneIndex = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        List<Task> tasks = this.taskService.getAllTask();
        for (Task task : tasks) {
            inMemoryLuceneIndex.indexDocument(task.getDescription());
        }
        Query query = new PrefixQuery(new Term("description", text));
        List<Document> documents = inMemoryLuceneIndex.searchIndex(query);
        for (Document document: documents) {
            result.add(document.get("description"));
        }
        return result;
    }

    /**
     * Task condition.
     * @param task Task.
     * @param radio status value.
     * @return task.
     */
    private Task condition(Task task, int radio) {
        if (radio == 1) {
            task.setTodo(true);
            task.setProgress(false);
            task.setDone(false);
        } else {
            if (radio == 2) {
                task.setTodo(false);
                task.setProgress(true);
                task.setDone(false);
            } else {
                task.setTodo(false);
                task.setProgress(false);
                task.setDone(true);
            }
        }
        return task;
    }
}
