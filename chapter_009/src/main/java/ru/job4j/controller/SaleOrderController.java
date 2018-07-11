package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SaleOrderController {

    @GetMapping(value = "/")
    public ModelAndView getSaleOrder() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }


}