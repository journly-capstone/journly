package com.capstone.journly.controllers;

import com.capstone.journly.models.User;
import com.capstone.journly.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public String saveUser(Model model, @Valid @ModelAttribute User user, Errors validation){

        String hash = encoder.encode(user.getPassword());

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/sign-up";
        } else if(userDao.findByUsername(user.getUsername()) != null) {
            model.addAttribute("username", user.getUsername());
            return "users/sign-up";
        } else if(userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("email", user.getUsername());
            return "users/sign-up";
        }

        user.setPassword(hash);
        userDao.save(user);

        model.addAttribute("title", user.getUsername());
        return "redirect:/login";
    }
}