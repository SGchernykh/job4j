package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final int id = Integer.parseInt(req.getParameter("id"));
        Users user = logic.findById(id);
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("<meta charset = utf-8>")
                .append("<h2>update user to database</h1>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/update method = post>", req.getContextPath()))
                .append(String.format("<input type = 'hidden' name = 'id' value = '%s'/>", id))
                .append(String.format("Name: <input type = 'text' name = 'name' value = '%s'/>", user.getName()))
                .append("<br />")
                .append(String.format("Login: <input type = 'text' name = 'login' value = '%s'/>", user.getLogin()))
                .append("<br />")
                .append(String.format("Email: <input type = 'text' name = 'email' value = '%s'/>", user.getEmail()))
                .append("<br />").append("<br />")
                .append("<input type = 'submit' name=action value = 'update'/>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        PrintWriter writer = resp.getWriter();
        writer.append(sb.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UsersServlet().doPost(req, resp);
        //resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
