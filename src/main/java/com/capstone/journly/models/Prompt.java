package com.capstone.journly.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prompts")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String prompt;


    @OneToMany(mappedBy = "prompt")
    private List<GratitudeEntry> gratitudeEntries;

    public Prompt() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<GratitudeEntry> getGratitudeEntries() {
        return gratitudeEntries;
    }

    public void setGratitudeEntries(List<GratitudeEntry> gratitudeEntries) {
        this.gratitudeEntries = gratitudeEntries;
    }
}
