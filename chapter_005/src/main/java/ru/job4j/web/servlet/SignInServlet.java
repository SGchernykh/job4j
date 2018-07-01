package ru.job4j.web.servlet;

/**
 * SignInServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.web.model.Users;
import ru.job4j.web.store.DBStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/signin.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        Users user = DBStore.getInstance().getUserByLoginAndPassword(login, password);
        HttpSession session = req.getSession();
        if (user != null) {
            session.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/html/indexAdmin.html").forward(req, resp);
            /*if (user.getRole().getRole().equals("admin")) {
                session.setAttribute("user", user);
                //resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
                req.getRequestDispatcher("/WEB-INF/html/indexAdmin.html").forward(req, resp);
            } else if (user.getRole().getRole().equals("user")) {
                session.setAttribute("user", user);
                resp.sendRedirect(String.format("%s/user", req.getContextPath()));
            }*/
        } else {
            req.setAttribute("error", "Credential invalid!");
            doGet(req, resp);
        }

    }
}
