package com.capstone.journly.controllers;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.PromptRepository;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//    @Value("${file-upload-path}")
//    private String uploadPath;


    public GratitudeEntryController(GratitudeEntryRepository gratitudeEntryDao, GratitudeEntryRepository gratitudeEntryDao1, UserRepository userDao, UserService userService, PromptRepository promptDao) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.userDao = userDao;
        this.userService = userService;
        this.promptDao = promptDao;
    }

    @GetMapping("/gratitude-board")
    public String gratitudeBoard(Model model) {
        List<GratitudeEntry> entries = gratitudeEntryDao.findAll();
        model.addAttribute("entries", entries);

        return "gratitudes/gratitude-entries";
    }

    // edit URL in below method later?
    @GetMapping(path ="/gratitude-board/create")
    public String createGratitudeEntryGET(Model model) {
        // use below methods for logged in user later
//        User user = userService.getLoggedInUser();
//        User loggedInUser = userDao.getOne(user.getId());
        model.addAttribute("newEntry", new GratitudeEntry());
        model.addAttribute("prompt", promptDao.findRandomPrompt());
        return "gratitudes/create-gratitude-entry";
    }


//    @PostMapping(path = "/gratitude-board/create")
//    public String upload(Model model, @RequestParam(name = "image") MultipartFile file, @ModelAttribute GratitudeEntry gratitudeEntry) {
//        String filename = file.getOriginalFilename();
//        String filepath = Paths.get(uploadPath, filename).toString();
//        File destinationFile = new File(filepath);
//        gratitudeEntry.setCreatedAt(new Date(System.currentTimeMillis()));
//        gratitudeEntry.setImgFilePath(filepath);
//        gratitudeEntry.setUser(userService.getLoggedInUser());
//        // need to include logic from the create-gratitude-entries template's checkbox
//        // if checkbox is checked --> isPublic == true
//        // if checkbox is NOT checked --> isPublic == false
////        gratitudeEntry.setIsPublic();
//
//        try {
//            file.transferTo(destinationFile);
//            gratitudeEntryDao.save(gratitudeEntry);
//            model.addAttribute("userResponse", "Upload successful.");
//        } catch(IOException e) {
//            e.printStackTrace();
//            model.addAttribute("userResponse", "Upload unsuccessful.");
//        }
//
//        return "redirect:/gratitudes/create-gratitude-entry";
//    }


//    @GetMapping("/gratitude-board/create")
//    public String showUploadFileForm(Model model) {
//        User currentUser = userService.getLoggedInUser();
//        User user = userDao.getOne(currentUser.getId());
//        model.addAttribute("newEntry", new GratitudeEntry());
//        return "gratitudes/create-gratitude-entry";
//    }

//    @PostMapping(path = "/gratitude-board/create")
//    public String createGratitudeEntryPOST (@ModelAttribute GratitudeEntry entry) {
//        User user = userService.getLoggedInUser();
//        entry.setUser(user);
//        GratitudeEntry savedEntry = gratitudeEntryDao.save(entry);
//
//        // map to single post view or profile view, etc
//        return "redirect:/gratitude-board";
//    }
}
