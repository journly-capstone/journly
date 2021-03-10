package com.capstone.journly.models;



import javax.persistence.*;
import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "gratitude_entry")
public class GratitudeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Date created_at;
    @Column(nullable = false)
    private Boolean is_public;
    @Column(nullable = true)
    private String img_file_path;
    @Column(nullable = false)
    private String body;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public GratitudeEntry() {
    }

    public GratitudeEntry(Date created_at, String body, long id, User user,Boolean is_public) {
        this.created_at = created_at;
        this.body = body;
        this.id = id;
        this.user = user;
        this.is_public = is_public;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DATETIME getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

    public String getImg_file_path() {
        return img_file_path;
    }

    public void setImg_file_path(String img_file_path) {
        this.img_file_path = img_file_path;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}