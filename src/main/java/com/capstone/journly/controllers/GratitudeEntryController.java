package com.capstone.journly.controllers;

import com.capstone.journly.models.*;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.LikeRepository;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GratitudeEntryController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final PromptRepository promptDao;
    private final LikeRepository likeDao;

    public GratitudeEntryController(GratitudeEntryRepository gratitudeEntryDao, UserRepository userDao, UserService userService, PromptRepository promptDao, LikeRepository likeDao) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.userDao = userDao;
        this.userService = userService;
        this.promptDao = promptDao;
        this.likeDao = likeDao;
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
        List<Like> likes = likeDao.findByGratitudeEntry(singleEntry);
        model.addAttribute("numOfLikes", likes.size());
        User user = userService.getLoggedInUser();
        List<Like> hasLiked = likeDao.findByGratitudeEntryAndUser(singleEntry, user);
        model.addAttribute("hasLiked", hasLiked.size()>0);
        return "gratitudes/individual-gratitude-entry";
    }

    @GetMapping(path ="/gratitude-board/create")
    public String createGratitudeEntryGET(Model model) {
        model.addAttribute("gratitudeEntry", new GratitudeEntry());
        Prompt prompt = promptDao.findRandomPrompt();
        model.addAttribute("prompt", prompt);
        return "gratitudes/create-gratitude-entry";
    }


    @PostMapping(path = "/gratitude-board/create")
    public String newGratitudeEntry(Model model,
                         @RequestParam(name = "image") MultipartFile image,
                         @RequestParam(name = "isPublic", defaultValue = "false") boolean isPublic,
                         // ********************** //
                         @RequestParam(name = "promptId") long promptId,
                         @ModelAttribute GratitudeEntry gratitudeEntry) {

        if (image != null) {
            String filename = image.getOriginalFilename();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);
            try {
                image.transferTo(destinationFile);
                gratitudeEntry.setImgFilePath("/uploads/" + filename);
                model.addAttribute("userResponse", "Upload successful.");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("userResponse", "Upload unsuccessful.");
            }
        } else {
            gratitudeEntry.setImgFilePath("/uploads/default.png");
        }

        gratitudeEntry.setUser(userService.getLoggedInUser());
        gratitudeEntry.setIsPublic(isPublic);
        gratitudeEntry.setPrompt(promptDao.getOne(promptId));
//        Date date = new Date();
//        DateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm");
//        String updatedDate = formatter.format(date);
//        model.addAttribute("updatedDate", updatedDate);
        gratitudeEntry.setCreatedAt(new Date());
        gratitudeEntryDao.save(gratitudeEntry);

        return "redirect:/gratitude-board";
    }

    @PostMapping("/like_gratitude_entry")
    public String likeEntry(@RequestParam("gratitudeId")long gratitudeId){
        System.out.println("***************************");
        System.out.println(gratitudeId);
        System.out.println("***************************");
        User user = userService.getLoggedInUser();
        GratitudeEntry gratitudeEntry = gratitudeEntryDao.getOne(gratitudeId);
        Like like = new Like();
        like.setUser(user);
        like.setGratitudeEntry(gratitudeEntry);
        likeDao.save(like);
        return"gratitudes/gratitude-board";
    }

    @PostMapping("/dashboard/{id}/delete/")
    public String deleteGratitudeEntry(@RequestParam(name = "entryId") long entryId, Model model) {
        gratitudeEntryDao.deleteById(entryId);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/{id}/update")
    public String updateEntryGET(@PathVariable(name="id") long id, Model model) {
        User current = userDao.getOne(userService.getLoggedInUser().getId());
        GratitudeEntry gratitudeEntry = gratitudeEntryDao.getOne(id);
        model.addAttribute("gratitudeEntry", gratitudeEntry);
        return "gratitudes/update-gratitude-entry";
    }

    @PostMapping("/dashboard/{id}/update")
    public String updateEntryPOST(
            @PathVariable(name = "id") long id,
            @RequestParam(name = "image") MultipartFile image,
            @RequestParam(name = "isPublic", defaultValue = "false") boolean isPublic,
            @ModelAttribute GratitudeEntry originalEntry) {

        GratitudeEntry updatedEntry = gratitudeEntryDao.getOne(id);

        if (image != null) {
            String filename = image.getOriginalFilename();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);
            try {
                image.transferTo(destinationFile);
                updatedEntry.setImgFilePath("/uploads/" + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updatedEntry.setUser(userService.getLoggedInUser());
        updatedEntry.setIsPublic(isPublic);

        gratitudeEntryDao.save(updatedEntry);

        return "redirect:/dashboard";
    }
}