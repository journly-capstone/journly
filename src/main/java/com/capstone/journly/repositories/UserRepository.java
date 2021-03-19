package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Like;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

}
