package com.capstone.journly.controllers;

import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.services.EmailService;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final EmailService emailService;
    private final UserService userService;

    public BookController(GratitudeEntryRepository gratitudeEntryDao, EmailService emailService, UserService userService) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.emailService = emailService;
        this.userService = userService;
    }

}
