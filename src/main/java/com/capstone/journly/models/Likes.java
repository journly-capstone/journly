package com.capstone.journly.models;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "gratitude_entry")
    private List<Likes> likes;


    public Likes(){}

    public Likes(long id, User user, List<Likes>likes){
    this.id = id;
    this.user = user;
    this.likes = likes;
}

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

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }
}
