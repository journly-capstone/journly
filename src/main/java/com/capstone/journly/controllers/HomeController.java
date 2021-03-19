package com.capstone.journly.controllers;
import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.QuoteRepository;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final QuoteRepository quoteDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final GratitudeEntryRepository gratitudeEntryDao;

    public HomeController(QuoteRepository quoteDao, UserRepository userDao, UserService userService, GratitudeEntryRepository gratitudeEntryDao) {
        this.quoteDao = quoteDao;
        this.userDao = userDao;
        this.userService = userService;
        this.gratitudeEntryDao = gratitudeEntryDao;
    }
//    private final PasswordEncoder encoder;
//    public HomeController(UserRepository userDao, PasswordEncoder encoder) {
//        this.userDao = userDao;
//        this.encoder = encoder;
//    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Journly - Gratitude for Teachers");
        model.addAttribute("firstUser", userDao.findRandomUser());
        model.addAttribute("secondUser", userDao.findRandomUser());
        model.addAttribute("thirdUser", userDao.findRandomUser());
        return "index";
    }

    @GetMapping("/home")
    public String welcome(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("title", "About Us");
        return "about-us";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        User user = userService.getLoggedInUser();
        User currentUser = userDao.getOne(user.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("quotes", quoteDao.findRandomQuote());
        model.addAttribute("userEntries", currentUser.getUserGratitudeEntries());
        return "users/dashboard";
    }



//    @GetMapping("/books")
//    public String booksSearchPage() {
//        return "books";
//    }
}