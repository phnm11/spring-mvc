package com.phnm.laptopshop.controller.admin;

import com.phnm.laptopshop.domain.User;
import com.phnm.laptopshop.service.UploadService;
import com.phnm.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;


    public UserController(UserService userService, UploadService uploadService) {
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = userService.getAllUsersByEmail("example@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("test", "test");
        model.addAttribute("check", "Hi from User Controller!");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @RequestMapping("/admin/user/{id}")
    public String getDetailUserPage(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUser(
            @ModelAttribute("newUser") User newUser,
            @RequestParam("userAvatar") MultipartFile file
    ) {

        String fileName = uploadService.handleSaveUploadFile(file, "avatar");
//        userService.saveUser(newUser);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(@ModelAttribute("newUser") User user) {
        User currentUser = userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setPhone(user.getPhone());
            currentUser.setFullName(user.getFullName());
            currentUser.setAddress(user.getAddress());

            userService.saveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@ModelAttribute("newUser") User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/admin/user";
    }

}
