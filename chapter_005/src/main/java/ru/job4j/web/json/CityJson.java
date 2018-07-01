package ru.job4j.web.json;
/**
 * CityJson.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.web.model.City;
import ru.job4j.web.model.Country;
import ru.job4j.web.store.DBStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        Country country = DBStore.getInstance().countryByName(req.getParameter("country"));
        List<City> countries = DBStore.getInstance().cityByNameCountry(country.getId());
        writer.append(mapper.writeValueAsString(countries));
        writer.flush();
    }
}
