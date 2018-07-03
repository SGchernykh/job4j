package ru.job4j.servlet.json;

/**
 * DriveUnitJsonServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.models.components.DriveUnit;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DriveUnitJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<DriveUnit> driveUnit = DBstore.getInstance().getAllDriveUnit();
        writer.append(mapper.writeValueAsString(driveUnit));
        writer.flush();
    }
}