package com.capstone.journly.models;


import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String api_id;

    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String readMore;

    @Column(nullable = true)
    private String bookThumbnail;

    @ManyToOne
    @JoinColumn(name = "bookshelf_id")
    private Bookshelf bookshelf;

    public Book() {}

    public Book(long id, String api_id, String author, String title, String bookThumbnail, String readMore) {
        this.id = id;
        this.api_id = api_id;
        this.author = author;
        this.title = title;
        this.bookThumbnail = bookThumbnail;
        this.readMore = readMore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApi_id() {
        return this.api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookThumbnail() {
        return bookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        this.bookThumbnail = bookThumbnail;
    }

    public String getReadMore() {
        return readMore;
    }

    public void setReadMore(String readMore) {
        this.readMore = readMore;
    }
}
