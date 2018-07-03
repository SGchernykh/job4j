package ru.job4j.servlet;

/**
 * SignInServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.models.User;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = DBstore.getInstance().getUserByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect("view.html");
        } else {
            req.setAttribute("error", "Credentials is not valid!");
            resp.sendRedirect("index.html");
        }
    }
}