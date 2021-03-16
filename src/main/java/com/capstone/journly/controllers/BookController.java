package com.capstone.journly.controllers;

import com.capstone.journly.models.Book;
import com.capstone.journly.models.Bookshelf;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.BookRepository;
import com.capstone.journly.repositories.BookshelfRepository;
import com.capstone.journly.repositories.GratitudeEntryRepository;
//import com.capstone.journly.services.EmailService;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final GratitudeEntryRepository gratitudeEntryDao;
//    private final EmailService emailService;
    private final UserService userService;
    private final BookRepository bookDao;
    private final BookshelfRepository bookshelfDao;

    public BookController(GratitudeEntryRepository gratitudeEntryDao, BookRepository bookDao, UserService userService, BookshelfRepository bookshelfDao) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.userService = userService;
        this.bookDao = bookDao;
        this.bookshelfDao = bookshelfDao;
    }

//    public BookController(GratitudeEntryRepository gratitudeEntryDao,BookRepository bookDao, EmailService emailService, UserService userService) {
//        this.gratitudeEntryDao = gratitudeEntryDao;
//        this.emailService = emailService;
//        this.userService = userService;
//        this.bookDao = bookDao;
//    }

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

    @GetMapping("/bookshelf")
    public String getBookshelf(Model model){
        model.addAttribute("title", "My Bookshelf");
        return "bookshelf";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam("id")String id, @RequestParam("author")String author, @RequestParam("title")String title){
        System.out.println(id);
        User user = userService.getLoggedInUser();
        Bookshelf bookshelf = bookshelfDao.findByUser(user);
        Book book = new Book();
        book.setApi_id(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setBookshelf(bookshelf);
        List<Book> books = new ArrayList<>();
        books.add(book);
        bookshelf.setBooks(books);
        bookshelfDao.save(bookshelf);
        return"redirect:/bookshelf";
    }


}
