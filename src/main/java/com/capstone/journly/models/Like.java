package com.capstone.journly.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // one user can have many likes
    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    private User user;

    // one post can have many likes
    // will need a join column if we do ManyToOne
    @OneToMany(mappedBy = "gratitude_entry_id")
    private List<Like> likes;


    public Like(){}

    public Like(long id, User user, List<Like>likes){
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

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
