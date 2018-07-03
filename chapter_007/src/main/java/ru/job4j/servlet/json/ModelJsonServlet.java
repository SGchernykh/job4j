package ru.job4j.servlet.json;

/**
 * ModelJsonServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.models.components.Brand;
import ru.job4j.models.components.Model;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ModelJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/json");
        String name = req.getParameter("brand");
        final Brand brand = DBstore.getInstance().findBrandByName(name);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        List<Model> models = DBstore.getInstance().getModelWithTheBrand(brand.getId());
        writer.append(mapper.writeValueAsString(models));
        writer.flush();
    }
}