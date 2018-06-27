package ru.job4j.web.servlet;

/**
 * AdminUpdateServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.store.DBStore;
import ru.job4j.web.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("user", ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("roles", DBStore.getInstance().roleAll());
        req.getRequestDispatcher("/WEB-INF/views/updateAdmin.jsp").forward(req, resp);
    }
}