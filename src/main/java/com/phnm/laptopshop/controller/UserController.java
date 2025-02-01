package com.phnm.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phnm.laptopshop.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = userService.handleHello();
        model.addAttribute("test", test);
        model.addAttribute("check", "Hi from User Controller!");
        return "hello";
    }
}

// @RestController
// public class UserController {

// @GetMapping("/")
// public String getHomePage() {
// return userService.handleHello();
// }
// }
