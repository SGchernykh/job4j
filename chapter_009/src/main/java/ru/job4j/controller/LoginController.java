package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView view = new ModelAndView();
        if (error != null) {
            view.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            view.addObject("logout", "Logged out successfully.");
        }
        view.setViewName("login");
        return view;
    }
}