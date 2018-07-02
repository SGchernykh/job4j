package ru.job4j.todolist.servlet;

/**
 * UpdateItemServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Item item = DBstore.getInstance().findById(Integer.parseInt(req.getParameter("id")));
        if (!item.getDone()) {
            item.setDone(true);
        } else {
            item.setDone(false);
        }
        DBstore.getInstance().update(item);
    }
}
