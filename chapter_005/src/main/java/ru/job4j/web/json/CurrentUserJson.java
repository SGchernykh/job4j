package ru.job4j.web.json;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.web.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CurrentUserJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/json");
        final HttpSession session = req.getSession();
        final Users user = (Users) session.getAttribute("currentUser");
        final PrintWriter writer = new PrintWriter(resp.getOutputStream());
        final ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(user));
        writer.flush();
    }
}