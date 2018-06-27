package ru.job4j.web.servlet;

/**
 * AdminViewServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.web.model.Role;
import ru.job4j.web.model.Users;
import ru.job4j.web.store.DBStore;
import ru.job4j.web.store.Validate;
import ru.job4j.web.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.function.Function;

public class AdminViewServlet extends HttpServlet {

    private final HashMap<String, Function<HttpServletRequest, Boolean>> operation = new HashMap<>();
    private final Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminViewServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", this.logic.findAll());
        req.getRequestDispatcher("/WEB-INF/views/indexAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        initAction();
        if (this.operation.containsKey(req.getParameter("action"))) {
            this.operation.get(req.getParameter("action")).apply(req);
        }
        resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
    }

    /**
     * Dispatch pattern.
     */
    private void initAction() {
        if (operation.size() == 0) {
            this.operation.put("add", req -> this.logic.add(new Users(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), new Timestamp(System.currentTimeMillis()), new Role(Integer.parseInt(req.getParameter("role_id")), req.getParameter("role")))));
            this.operation.put("update", req -> this.logic.update(new Users(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), new Timestamp(System.currentTimeMillis()), new Role(Integer.parseInt(req.getParameter("role_id")), req.getParameter("role")))));
            this.operation.put("delete", req -> AdminViewServlet.this.logic.delete(Integer.parseInt(req.getParameter("id"))));
        }
    }
}