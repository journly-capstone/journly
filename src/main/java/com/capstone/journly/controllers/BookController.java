package com.capstone.journly.controllers;
import com.capstone.journly.models.Book;
import com.capstone.journly.models.Bookshelf;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.BookRepository;
import com.capstone.journly.repositories.BookshelfRepository;
import com.capstone.journly.repositories.GratitudeEntryRepository;
//import com.capstone.journly.services.EmailService;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final GratitudeEntryRepository gratitudeEntryDao;
    //    private final EmailService emailService;
    private final UserService userService;
    private final BookRepository bookDao;
    private final BookshelfRepository bookshelfDao;
    private final UserRepository userDao;
    public BookController(GratitudeEntryRepository gratitudeEntryDao, BookRepository bookDao, UserService userService, BookshelfRepository bookshelfDao, UserRepository userDao) {
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.userService = userService;
        this.bookDao = bookDao;
        this.bookshelfDao = bookshelfDao;
        this.userDao = userDao;
    }
    //    public BookController(GratitudeEntryRepository gratitudeEntryDao,BookRepository bookDao, EmailService emailService, UserService userService) {
//        this.gratitudeEntryDao = gratitudeEntryDao;
//        this.emailService = emailService;
//        this.userService = userService;
//        this.bookDao = bookDao;
//    }
//    Line 49 can possibly be removed*****************************
    @GetMapping("/books")
    public String postForm(Model model){
        model.addAttribute("book", new Book());
        return "books";
    }
    //    @PostMapping("/books")
//    public String createPost(@ModelAttribute Book book) {
//        // Will throw if no users in the db!
//        // In the future, we will get the logged in user
//        User user = userService.getLoggedInUser();
//
//
//        Book savedBook = bookDao.save(book);
//
//
//        return "redirect:/books";
//    }
    @GetMapping("/bookshelf")
    public String getBookshelf(Model model){
        model.addAttribute("title", "My Bookshelf");
        User user = userService.getLoggedInUser();
        Bookshelf bookshelf = bookshelfDao.findByUser(user);
        List<Book> books = bookshelf.getBooks();
        model.addAttribute("books", books);
        return "bookshelf";
    }
    @PostMapping("/addbook")
    public String addBook(
            @RequestParam("bookThumbnail")String bookThumbnail,
            @RequestParam("id")String id,
            @RequestParam("author")String author,
            @RequestParam("title")String title){
        System.out.println(id);
        User user = userService.getLoggedInUser();
        Bookshelf bookshelf = bookshelfDao.findByUser(user);
        Book book = new Book();
        book.setApi_id(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setBookThumbnail(bookThumbnail);
        book.setBookshelf(bookshelf);
        List<Book> books = new ArrayList<>();
        books.add(book);
        bookshelf.setBooks(books);
        bookshelfDao.save(bookshelf);
        return"redirect:/bookshelf";
    }
    @PostMapping("/deletebook")
    public String deleteBook(@RequestParam("deleteID")long deleteID, Model model){
        System.out.println(deleteID);
        System.out.println("*******************************************");
        User user = userService.getLoggedInUser();
        Bookshelf bookshelf = bookshelfDao.findByUser(user);
        Book book = bookDao.getOne(deleteID);
        bookDao.delete(book);
        bookshelfDao.save(bookshelf);
        Bookshelf updatedBookshelf = bookshelfDao.findByUser(user);
        List<Book> books = updatedBookshelf.getBooks();
        model.addAttribute("books", books);
        return "partials/updateBookshelf :: updatedBooks";
    }
//    @GetMapping("/loadbookshelf")
//    public String loadBookshelf(Model model){
//        User user = userService.getLoggedInUser();
//        Bookshelf bookshelf = bookshelfDao.findByUser(user);
//        List<Book> books = bookshelf.getBooks();
//        model.addAttribute("books", books);
//        return "bookshelf";
//    }
}
