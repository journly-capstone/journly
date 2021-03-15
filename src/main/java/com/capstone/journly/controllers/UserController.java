package com.capstone.journly.controllers;

import com.capstone.journly.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }


}