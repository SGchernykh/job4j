package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("<meta charset = utf-8>")
                .append("<h2>Add new user to database</h1>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/add method = post>", req.getContextPath()))
                .append("Name: <input type = 'text' name = 'name'/>").append("<br />")
                .append("Login: <input type = 'text' name = 'login'/>").append("<br />")
                .append("Email: <input type = 'text' name = 'email'/>").append("<br />").append("<br/>")
                .append("<input type = 'submit' value = 'add' name='action'/>")
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
    }
}
