package com.capstone.journly.models;

import javax.persistence.*;

@Entity
@Table(name = "prompts")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String prompt;

    @OneToOne
    private GratitudeEntry gratitudeEntry;

    public Prompt() {}

    public Prompt(Prompt copy) {
        this.id = copy.id;
        this.prompt = copy.prompt;
    }

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

    public GratitudeEntry getGratitudeEntry() {
        return gratitudeEntry;
    }

    public void setGratitudeEntry(GratitudeEntry gratitudeEntry) {
        this.gratitudeEntry = gratitudeEntry;
    }

}
