package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.models.User;
import ru.job4j.service.UserService;

@Controller
@RequestMapping("/register")
public class RegController {
    private UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView registerPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("register");
        return view;
    }

    @PostMapping
    public ModelAndView regUser(final User user) {
        ModelAndView view = new ModelAndView();
        User u = this.userService.getByLogin(user.getLogin());
        if (u != null) {
            view.setViewName("register");
            view.addObject("error", true);
            return view;
        }
        view.setViewName("index");
        this.userService.regUser(user);
        return view;
    }
}
