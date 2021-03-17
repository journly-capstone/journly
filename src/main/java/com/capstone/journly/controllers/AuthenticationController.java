package com.capstone.journly.controllers;

import com.capstone.journly.models.Bookshelf;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.BookshelfRepository;
import com.capstone.journly.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private final UserRepository userDao;
    private final PasswordEncoder encoder;
    private final BookshelfRepository bookshelfDao;

    public AuthenticationController(UserRepository userDao, PasswordEncoder encoder, BookshelfRepository bookshelfDao) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.bookshelfDao = bookshelfDao;
    }

    //Show User the Login Form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    //Show User the Sign Up Form
    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    //Save User to Database
    @PostMapping("/sign-up")
    public String saveUser(Model model, @Valid @ModelAttribute User user, Errors validation, @RequestParam(name = "confirm") String confirm){

        String hash = encoder.encode(user.getPassword());

        if(!user.getPassword().equals(confirm)){
            validation.rejectValue(
                    "password",
                    "user.password",
                    "Passwords do not match"
            );
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/sign-up";
        } else if((userDao.findByUsername(user.getUsername()) != null) && (userDao.findByEmail(user.getEmail()) != null)) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "users/sign-up";
        } else if(userDao.findByUsername(user.getUsername()) != null) {
            model.addAttribute("username", user.getUsername());
            return "users/sign-up";
        } else if(userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("email", user.getEmail());
            return "users/sign-up";
        }

        user.setPassword(hash);
        user.setImgFilePath("/uploads/default-profile-picture.png");


        userDao.save(user);
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setUser(user);
        bookshelf.setId(user.getId());
        bookshelfDao.save(bookshelf);

        model.addAttribute("title", user.getUsername());
        return "redirect:/login";
    }
}