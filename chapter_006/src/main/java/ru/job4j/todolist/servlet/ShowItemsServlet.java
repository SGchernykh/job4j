package ru.job4j.todolist.servlet;

/**
 * ShowItemsServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> items = DBstore.getInstance().getAll();
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(items));
        writer.flush();
    }
}