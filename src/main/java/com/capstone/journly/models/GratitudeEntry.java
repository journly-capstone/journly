package com.capstone.journly.models;



import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.Multipart;
import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "gratitude_entry")
public class GratitudeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = true)
    @Value("${file-upload-path}")
    private String imgFilePath;

    @Column(nullable = false, length = 1200)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    public GratitudeEntry() {
    }

    public GratitudeEntry(Date createdAt, String body, long id, User user,Boolean isPublic) {
        this.createdAt = createdAt;
        this.body = body;
        this.id = id;
        this.user = user;
        this.isPublic = isPublic;
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