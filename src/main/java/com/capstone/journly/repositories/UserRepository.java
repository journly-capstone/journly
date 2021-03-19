package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Like;
import com.capstone.journly.models.Prompt;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Query(value = "SELECT * FROM users ORDER BY RAND() LIMIT 1", nativeQuery = true)
    public User findRandomUser();

}
