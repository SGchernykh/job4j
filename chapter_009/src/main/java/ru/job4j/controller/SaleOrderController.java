package ru.job4j.controller;

/**
 * SaleOrderController.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.models.ModelForFillingOrder;
import ru.job4j.models.SaleOrder;
import ru.job4j.service.SaleOrderService;

import java.util.List;

@Controller
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @GetMapping(value = "/")
    public ModelAndView getSaleOrder() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    @GetMapping("/list")
    public @ResponseBody
    List<SaleOrder> getAll() {
        return this.saleOrderService.getAll();
    }

    @GetMapping(value = "/add")
    public ModelAndView getAddView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("addOrder");
        return view;
    }

    @PostMapping(value = "/update")
    public String getList(@RequestParam int id) {
        SaleOrder saleOrder = this.saleOrderService.getById(id);
        saleOrder.setSale(!saleOrder.isSale());
        this.saleOrderService.save(saleOrder);
        return "redirect:/";
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addAdvert(ModelForFillingOrder modelToAdvert, @RequestParam("upFile") MultipartFile file) {
        this.saleOrderService.save(this.saleOrderService.prepareSaleOrder(modelToAdvert, file));
        return "redirect:/";
    }
}