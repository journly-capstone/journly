package com.capstone.journly.controllers;

import com.capstone.journly.models.Book;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.BookRepository;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.services.EmailService;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final GratitudeEntryRepository gratitudeEntryDao;
    private final EmailService emailService;
    private final UserService userService;
    private final BookRepository bookDao;

    public BookController(GratitudeEntryRepository gratitudeEntryDao,BookRepository bookDao, EmailService emailService, UserService userService) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.emailService = emailService;
        this.userService = userService;
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    public String postForm(Model model){
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping("/books")
    public String createPost(@ModelAttribute Book book) {
        // Will throw if no users in the db!
        // In the future, we will get the logged in user
        User user = userService.getLoggedInUser();


        Book savedBook = bookDao.save(book);


        return "redirect:/books";
    }

}
