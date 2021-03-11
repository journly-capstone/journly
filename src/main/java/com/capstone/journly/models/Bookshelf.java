package com.capstone.journly.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bookshelves")
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "bookshelf", cascade = CascadeType.ALL)
    private List<Book> books;

    public Bookshelf(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
