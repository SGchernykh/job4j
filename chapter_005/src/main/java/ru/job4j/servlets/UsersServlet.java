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

public class UsersServlet extends HttpServlet {
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private final Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("" + this.logic.findAll());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    operation(req.getParameter("action"), req);
    }

    /**
     * Command pattern.
     * @param operation Action.
     * @param req HttpServletRequest.
     * @return True success.
     */
    public boolean operation(final String operation, HttpServletRequest req) {
        final boolean result;
        switch (operation.toLowerCase()) {
            case ADD:
                this.logic.add(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
                System.out.println("add");
                result = true;
                break;
            case DELETE:
                this.logic.delete(Integer.parseInt(req.getParameter("id")));
                System.out.println("deleted");
                result = true;
                break;
            case UPDATE:
                this.logic.update(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
                result = true;
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}
