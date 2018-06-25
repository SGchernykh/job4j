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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         StringBuilder start = new StringBuilder();
        //String create = String.format("<form action=%s/add method=get><input type='hidden' value='%s'/>%s", req.getContextPath(), "<input type='submit' value='add'/></form>");
        start.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("    <meta charset=\"UTF-8\">")
                .append("    <title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<form action='" + req.getContextPath() + "/users' method='post'>")
                .append("<table border = '1'>")
                .append("<tr>")
                .append("<td>ID</td>")
                .append("<td>Name</td>")
                .append("<td>Login</td>")
                .append("<td>Email</td>")
                .append("<td>CreateData</td>")
                .append("<th>Update</th>")
                .append("<th>Delete</th>")
                .append("</tr>")
                .append("</form>");


        for (Users user : this.logic.findAll()) {
            String update = String.format("<form action=%s/update method=get><input type='hidden' name='id' value='%s'/>%s", req.getContextPath(), user.getId(), "<input type='submit' value='update'/></form>");
            String delete = String.format("<form action=%s/users method=post><input type='hidden' name='id' value='%s'/>%s", req.getContextPath(), user.getId(), "<input type='submit' name='action' value='delete'/></form>");
            start.append("<tr>")
                    .append("<td>" + user.getId() + "</td>")
                    .append("<td>" + user.getName() + "</td>")
                    .append("<td>" + user.getLogin() + "</td>")
                    .append("<td>" + user.getEmail() + "</td>")
                    .append("<td>" + user.getCreateDate() + "</td>")
                    .append("<th>" + update + "</th>")
                    .append("<th>" + delete + "</th>")
                    .append("</tr>");

        }
        start.append("</table>")
                .append("</table>")

                .append(String.format("<form action = %s/add method = get>", req.getContextPath()))
                .append("<input type='submit' value='add'/></form>")
                .append("</body>")
                .append("</html>");
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(start.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initAction();
        if (this.operation.containsKey(req.getParameter("action"))) {
            this.operation.get(req.getParameter("action")).apply(req);
        }
        doGet(req, resp);
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
