package com.capstone.journly.controllers;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GratitudeEntryController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final UserRepository usersDao;
//    private final EmailService emailService;
//    private final UserService userService;


    public GratitudeEntryController(GratitudeEntryRepository gratitudeEntryDao, GratitudeEntryRepository gratitudeEntryDao1, UserRepository usersDao) {
        this.gratitudeEntryDao = gratitudeEntryDao1;
        this.usersDao = usersDao;
    }

    @GetMapping("/gratitude-board")
    public String gratitudeBoard(Model model) {
        List<GratitudeEntry> entries = gratitudeEntryDao.findAll();
        model.addAttribute("entries", entries);

        return "gratitude-entries";
    }
}
