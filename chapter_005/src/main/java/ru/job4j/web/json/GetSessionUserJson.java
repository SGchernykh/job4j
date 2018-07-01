package ru.job4j.web.json;
/**
 * GetSessionUserJson.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.web.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class GetSessionUserJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        final HttpSession session = req.getSession();
        final Users user = (Users) session.getAttribute("user");
        final PrintWriter writer = new PrintWriter(resp.getOutputStream());
        final ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(user));
        writer.flush();
    }
}