package com.capstone.journly.controllers;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GratitudeEntryController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final UserRepository usersDao;
//    private final EmailService emailService;
    private final UserService userService;


    public GratitudeEntryController(GratitudeEntryRepository gratitudeEntryDao, GratitudeEntryRepository gratitudeEntryDao1, UserRepository usersDao, UserService userService) {
        this.gratitudeEntryDao = gratitudeEntryDao1;
        this.usersDao = usersDao;
        this.userService = userService;
    }

    @GetMapping("/gratitude-board")
    public String gratitudeBoard(Model model) {
        List<GratitudeEntry> entries = gratitudeEntryDao.findAll();
        model.addAttribute("entries", entries);

        return "gratitude-entries";
    }

    // edit URL in below method later?
    @GetMapping(path ="/gratitude-board/create")
    public String createGratitudeEntryGET(Model model) {
        model.addAttribute("newEntry", new GratitudeEntry());
        return "create-gratitude-entry";
    }

    @PostMapping(path = "/gratitude-board/create")
    public String createGratitudeEntryPOST (@ModelAttribute GratitudeEntry entry) {
        User user = userService.getLoggedInUser();
        entry.setUser(user);
        GratitudeEntry savedEntry = gratitudeEntryDao.save(entry);

        // map to single post view or profile view, etc
        return "redirect:/gratitude-board";
    }
}
