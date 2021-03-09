package com.capstone.journly.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String img_file_path;

    @OneToMany(mappedBy = "user")
    private List<GratitudeEntry> posts;

    public User(){

    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.password = copy.password;
        this.email = copy.email;
        this.img_file_path = copy.img_file_path;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg_file_path() {
        return img_file_path;
    }

    public void setImg_file_path(String img_file_path) {
        this.img_file_path = img_file_path;
    }


}