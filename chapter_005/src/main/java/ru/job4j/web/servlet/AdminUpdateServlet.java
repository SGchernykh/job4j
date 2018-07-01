package ru.job4j.web.servlet;

/**
 * AdminUpdateServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.Users;
import ru.job4j.web.store.DBStore;
import ru.job4j.web.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final Users user = DBStore.getInstance().findById(id);
        final HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);
        req.getRequestDispatcher("/WEB-INF/html/updateAdmin.html").forward(req, resp);
    }
}