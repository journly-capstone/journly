package com.capstone.journly.controllers;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Prompt;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.PromptRepository;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class GratitudeEntryController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final PromptRepository promptDao;
//    private final EmailService emailService;



    public GratitudeEntryController(GratitudeEntryRepository gratitudeEntryDao, GratitudeEntryRepository gratitudeEntryDao1, UserRepository userDao, UserService userService, PromptRepository promptDao) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.userDao = userDao;
        this.userService = userService;
        this.promptDao = promptDao;
    }

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/gratitude-board")
    public String gratitudeBoard(Model model) {
        List<GratitudeEntry> entries = gratitudeEntryDao.findAll();
        model.addAttribute("entries", entries);

        return "gratitudes/gratitude-board";
    }

    @GetMapping("/gratitude-board/{id}")
    public String singleEntryViewMore (Model model, @PathVariable long id) {
        GratitudeEntry singleEntry = gratitudeEntryDao.getOne(id);
        model.addAttribute("singleEntry", singleEntry);
        return "gratitudes/individual-gratitude-entry";
    }

    @GetMapping(path ="/gratitude-board/create")
    public String createGratitudeEntryGET(Model model) {
        model.addAttribute("newEntry", new GratitudeEntry());
        Prompt prompt = promptDao.findRandomPrompt();
        model.addAttribute("prompt", prompt.getPrompt());
        return "gratitudes/create-gratitude-entry";
    }


    @PostMapping(path = "/gratitude-board/create")
    public String upload(Model model,
                         @RequestParam(name = "image") MultipartFile image,
                         @RequestParam(name = "isPublic") Boolean isPublic,
                         @RequestParam(name = "currentPrompt") Prompt currentPrompt,
                         @ModelAttribute GratitudeEntry gratitudeEntry) {

        if (image != null) {
            String filename = image.getOriginalFilename();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);
            try {
                image.transferTo(destinationFile);
                gratitudeEntry.setImgFilePath("/uploads" + filename);
                model.addAttribute("userResponse", "Upload successful.");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("userResponse", "Upload unsuccessful.");
            }
        } else {
            gratitudeEntry.setImgFilePath("/uploads/default-profile-picture.png");
        }

        // working on the logic for the isPublic checkbox
        // need to include logic from the create-gratitude-entries template's checkbox
        // if checkbox is checked --> isPublic == true
        // if checkbox is NOT checked --> isPublic == false
        if (isPublic != true) {
            gratitudeEntry.setIsPublic(false);
        } else {
            gratitudeEntry.setIsPublic(true);
        }
        gratitudeEntry.setUser(userService.getLoggedInUser());
        gratitudeEntry.setPrompt(currentPrompt);
        gratitudeEntry.setCreatedAt(new Date(System.currentTimeMillis()));
        gratitudeEntryDao.save(gratitudeEntry);

        return "redirect:/gratitudes/gratitude-board";
    }

}
