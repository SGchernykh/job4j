package ru.job4j.web.servlet;

/**
 * AdminViewServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.web.model.City;
import ru.job4j.web.model.Country;
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
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class AdminViewServlet extends HttpServlet {

    private final HashMap<String, Function<HttpServletRequest, Boolean>> operation = new HashMap<>();
    private final Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminViewServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        List<Users> users = this.logic.findAll();
        writer.append(mapper.writeValueAsString(users));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Role role = null;
        Country country = null;
        City city = null;
        if ("add".equals(req.getParameter("action")) || "update".equals(req.getParameter("action"))) {
            role = DBStore.getInstance().roleByName(req.getParameter("role"));
            country = DBStore.getInstance().countryByName(req.getParameter("country"));
            city = DBStore.getInstance().cityByName(req.getParameter("city"));
        }
        initAction(role, country, city);
        if (this.operation.containsKey(req.getParameter("action"))) {
            this.operation.get(req.getParameter("action")).apply(req);
        }
        //resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
        req.getRequestDispatcher("/WEB-INF/html/indexAdmin.html").forward(req, resp);
    }

    /**
     * Dispatch pattern.
     */
    private void initAction(Role role, Country country, City city) {
        if (operation.size() == 0) {
            this.operation.put("add", req -> this.logic.add(new Users(0, req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), new Timestamp(System.currentTimeMillis()), role, country, city)));
            this.operation.put("update", req -> this.logic.update(new Users(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), new Timestamp(System.currentTimeMillis()), role, country, city)));
            this.operation.put("delete", req -> this.logic.delete(Integer.parseInt(req.getParameter("id"))));
        }
    }
}