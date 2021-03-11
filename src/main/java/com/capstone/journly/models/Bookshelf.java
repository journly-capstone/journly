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



}
