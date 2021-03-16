package com.capstone.journly.controllers;

import com.capstone.journly.models.User;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public UserController(UserRepository userDao, PasswordEncoder encoder, UserService userService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.userService = userService;
    }

    //Show Profile Settings to User
    @GetMapping("/profile-settings")
    public String showProfileSettings(Model model) {
        model.addAttribute("title", "Profile Settings");

        User sessionUser = userService.getLoggedInUser();
        User user = userDao.getOne(sessionUser.getId());
        model.addAttribute("user", user);

        return "users/profile-settings";
    }

    //Allow User to Update Profile Settings
    @PostMapping("/profile-settings")
    public String updateProfileSettings(@ModelAttribute User user, Model model) {
        User sessionUser = userService.getLoggedInUser();
        User updatedUser = userDao.getOne(sessionUser.getId());

        user.setId(updatedUser.getId());
        user.setPassword(updatedUser.getPassword());
        userDao.save(user);

        return "redirect:/profile-settings";
    }
}