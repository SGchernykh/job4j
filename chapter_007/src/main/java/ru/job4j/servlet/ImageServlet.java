package ru.job4j.servlet;

/**
 * ImageServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.store.DBstore;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int imageId = Integer.parseInt(req.getPathInfo().substring(1));
        resp.setContentType("image/png");
        File file = new File(DBstore.getInstance().getPhotoById(imageId).getImageURL());
        BufferedImage image = ImageIO.read(file);
        try (OutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "png", out);
        }
    }
}