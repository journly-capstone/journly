package com.capstone.journly.models;



import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gratitude_entry")
public class GratitudeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    @Column(nullable = false, length = 1200)
    private String body;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = true)
    @Value("${file-upload-path}")
    private String imgFilePath;



    public GratitudeEntry() {
    }

    public GratitudeEntry(long id, User user, Prompt prompt, Date createdAt, Boolean isPublic, String imgFilePath, String body) {
        this.id = id;
        this.user = user;
        this.prompt = prompt;
        this.createdAt = createdAt;
        this.isPublic = isPublic;
        this.imgFilePath = imgFilePath;
        this.body = body;
    }

    public GratitudeEntry(Prompt prompt, String body, Date createdAt, Boolean isPublic, String imgFilePath) {
        this.body = body;
        this.prompt = prompt;
        this.createdAt = createdAt;
        this.isPublic = isPublic;
        this.imgFilePath = imgFilePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
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

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

}