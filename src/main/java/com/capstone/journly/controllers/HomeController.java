package com.capstone.journly.controllers;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class HomeController {
//    private final UserRepository userDao;
//    private final PasswordEncoder encoder;
//    public HomeController(UserRepository userDao, PasswordEncoder encoder) {
//        this.userDao = userDao;
//        this.encoder = encoder;
//    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Journly");
        return "index";
    }

    @GetMapping("/home")
    public String welcome(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }

//    @GetMapping("/books")
//    public String booksSearchPage() {
//        return "books";
//    }
}