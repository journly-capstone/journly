package com.capstone.journly.repositories;

import com.capstone.journly.models.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
    @Query(value = "SELECT * FROM prompts ORDER BY RAND() LIMIT 1", nativeQuery = true)
    public Prompt findRandomPrompt();

}
