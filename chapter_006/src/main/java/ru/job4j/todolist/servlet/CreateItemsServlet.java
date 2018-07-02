package ru.job4j.todolist.servlet;
/**
 * CreateItemsServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateItemsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        boolean done = false;
        if (req.getParameter("done") != null) {
            done = true;
        }
        Item item = new Item();
        item.setDescription(req.getParameter("data"));
        item.setDone(done);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        DBstore.getInstance().createItem(item);
    }
}