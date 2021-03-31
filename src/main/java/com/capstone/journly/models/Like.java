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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // one gratitude_entry can have many likes
    // will need a join column if we do ManyToOne
    @ManyToOne
    @JoinColumn(name = "gratitude_entry_id")
    private GratitudeEntry gratitudeEntry;


    public Like(){}

    public Like(long id, User user, GratitudeEntry gratitudeEntry){
    this.id = id;
    this.user = user;
    this.gratitudeEntry = gratitudeEntry;
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

    public GratitudeEntry getGratitudeEntry() {
        return gratitudeEntry;
    }

    public void setGratitudeEntry(GratitudeEntry gratitudeEntry) {
        this.gratitudeEntry = gratitudeEntry;
    }
}
