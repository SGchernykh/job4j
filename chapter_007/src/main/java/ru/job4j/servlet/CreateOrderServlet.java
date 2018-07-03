package ru.job4j.servlet;

/**
 * CreateOrderServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.models.Car;
import ru.job4j.models.Photo;
import ru.job4j.models.SaleOrder;
import ru.job4j.models.User;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CreateOrderServlet extends HttpServlet {
    private final ConcurrentMap<String, String> formField = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Photo photo = null;
        try {
            List<FileItem> items = fileUpload.parseRequest(req);
            photo = fillMap(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int carBodyId = Integer.parseInt(this.formField.get("body"));
        final int modelId = Integer.parseInt(this.formField.get("model"));
        final int engineId = Integer.parseInt(this.formField.get("engine"));
        final int transmissionId = Integer.parseInt(this.formField.get("transmission"));
        final int driveUnitId = Integer.parseInt(this.formField.get("drive_unit"));
        final int cityId = Integer.parseInt(this.formField.get("city"));
        final int price = Integer.parseInt(this.formField.get("price"));
        Car car = new Car();
        car.setBrand(DBstore.getInstance().findBrandByName(this.formField.get("brand")));
        car.setModel(DBstore.getInstance().findModelById(modelId));
        car.setCarBody(DBstore.getInstance().findCarBodyById(carBodyId));
        car.setEngine(DBstore.getInstance().findEngineById(engineId));
        car.setTransmission(DBstore.getInstance().findTransmissionById(transmissionId));
        car.setDriveUnit(DBstore.getInstance().findDriveUnitById(driveUnitId));
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setTitle(this.formField.get("title"));
        saleOrder.setDescription(this.formField.get("description"));
        saleOrder.setSale(false);
        saleOrder.setAuthor(user);
        saleOrder.setCreated(new Timestamp(System.currentTimeMillis()));
        saleOrder.setCity(DBstore.getInstance().findCityById(cityId));
        saleOrder.setPrice(price);
        saleOrder.setPhoto(photo);
        saleOrder.setCar(car);
        DBstore.getInstance().addSaleOrder(photo, car, saleOrder);
        resp.sendRedirect("view.html");
    }

    /**
     * Fill map parameter
     * @param items List.
     * @return Photo
     * @throws Exception
     */
    private Photo fillMap(final List<FileItem> items) throws Exception {
        Photo photo = null;
        this.formField.clear();
        for (FileItem item : items) {
            if (item.isFormField()) {
                formField.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                File dir = new File("C:\\projects\\job4j\\chapter_007\\target\\image");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir.getAbsolutePath() + "/" + item.getName());
                item.write(file);
                photo = new Photo();
                photo.setName(file.getName());
                photo.setImageURL(file.getCanonicalPath());
            }
        }
        return photo;
    }
}