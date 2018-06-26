package ru.job4j.servlets;

/**
 * UsersServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.function.Function;

public class UsersServlet extends HttpServlet {

    private final HashMap<String, Function<HttpServletRequest, Boolean>> operation = new HashMap<>();
    private final Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        initAction();
        if (this.operation.containsKey(req.getParameter("action"))) {
            this.operation.get(req.getParameter("action")).apply(req);
        }
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

    /**
     * Dispatch pattern.
     */
    private void initAction() {
        if (operation.size() == 0) {
            this.operation.put("add", req -> this.logic.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
            this.operation.put("update", req -> this.logic.update(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
            this.operation.put("delete", req -> UsersServlet.this.logic.delete(Integer.parseInt(req.getParameter("id"))));
        }
    }
}