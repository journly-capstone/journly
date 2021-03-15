package com.capstone.journly.controllers;
import com.capstone.journly.repositories.QuoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final QuoteRepository quoteDao;

    public HomeController(QuoteRepository quoteDao) {
        this.quoteDao = quoteDao;
    }
//    private final UserRepository userDao;
//    private final PasswordEncoder encoder;
//    public HomeController(UserRepository userDao, PasswordEncoder encoder) {
//        this.userDao = userDao;
//        this.encoder = encoder;
//    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Journly - Gratitude for Teachers");
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
        model.addAttribute("title", "Dashboard");
        model.addAttribute("quotes", quoteDao.findRandomQuote());
        return "users/dashboard";
    }



//    @GetMapping("/books")
//    public String booksSearchPage() {
//        return "books";
//    }
}