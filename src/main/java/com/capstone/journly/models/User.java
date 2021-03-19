package com.capstone.journly.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, message = "Username must be at least 3 characters in length")
    @NotBlank(message ="*Required")
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Email(message = "Invalid email address")
    @NotBlank(message ="*Required")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters in length")
    @NotBlank(message = "*Required")
    @Column(nullable = false)
    private String password;

    // Update to nullable = false when we include a default image in the code
    @Column(nullable = true)
    private String imgFilePath;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GratitudeEntry> userGratitudeEntries;


    public User(){

    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.password = copy.password;
        this.email = copy.email;
        this.imgFilePath = copy.imgFilePath;

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

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public List<GratitudeEntry> getUserGratitudeEntries() {
        return userGratitudeEntries;
    }

    public void setUserGratitudeEntries(List<GratitudeEntry> userGratitudeEntries) {
        this.userGratitudeEntries = userGratitudeEntries;
    }

}