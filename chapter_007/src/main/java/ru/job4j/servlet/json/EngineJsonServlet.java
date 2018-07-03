package ru.job4j.servlet.json;

/**
 * EngineJsonServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.models.components.ComponentsCar;
import ru.job4j.models.components.Engine;
import ru.job4j.store.DBstore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EngineJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<Engine> engine = DBstore.getInstance().getAllEngine();
        writer.append(mapper.writeValueAsString(engine));
        writer.flush();
    }
}