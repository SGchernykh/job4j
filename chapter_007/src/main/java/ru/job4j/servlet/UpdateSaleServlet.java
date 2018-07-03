package ru.job4j.servlet;

/**
 * UpdateSaleServlet.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.models.SaleOrder;
import ru.job4j.store.DBstore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateSaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int orderId = Integer.parseInt(req.getParameter("id"));
        SaleOrder saleOrder = DBstore.getInstance().getOrderById(orderId);
        saleOrder.setSale(!saleOrder.isSale());
        DBstore.getInstance().updateSaleStatus(saleOrder);
        resp.sendRedirect("view.html");
    }
}