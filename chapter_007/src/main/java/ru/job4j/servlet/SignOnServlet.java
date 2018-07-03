package ru.job4j.servlet;

/**
 * SignOnServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.models.User;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("regPassword");
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        DBstore.getInstance().addUser(user);
        resp.sendRedirect("index.html");
    }
}