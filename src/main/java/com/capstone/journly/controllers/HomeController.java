package com.capstone.journly.controllers;
import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Like;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.LikeRepository;
import com.capstone.journly.repositories.QuoteRepository;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {
    private final QuoteRepository quoteDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final GratitudeEntryRepository gratitudeEntryDao;
    private final LikeRepository likeDao;

    public HomeController(QuoteRepository quoteDao, UserRepository userDao, UserService userService, GratitudeEntryRepository gratitudeEntryDao, LikeRepository likeDao) {
        this.quoteDao = quoteDao;
        this.userDao = userDao;
        this.userService = userService;
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.likeDao = likeDao;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Journly - Gratitude for Teachers");
        model.addAttribute("firstUser", userDao.findRandomUser());
        model.addAttribute("secondUser", userDao.findRandomUser());
        model.addAttribute("thirdUser", userDao.findRandomUser());
        return "index";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("title", "About Us");
        return "about-us";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        List<GratitudeEntry> entries = gratitudeEntryDao.findAll();
        HashMap<Long, Integer> numOfLikes = new HashMap<Long, Integer>();
        User user = userService.getLoggedInUser();
        User currentUser = userDao.getOne(user.getId());

        for (GratitudeEntry entry : entries) {

            List<Like> likes = likeDao.findByGratitudeEntry(entry);
            numOfLikes.put(entry.getId(), likes.size());

        }

        model.addAttribute("numOfLikes", numOfLikes);
        model.addAttribute("user", currentUser);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("quotes", quoteDao.findRandomQuote());
        model.addAttribute("userEntries", currentUser.getUserGratitudeEntries());
        return "users/dashboard";

    }
}