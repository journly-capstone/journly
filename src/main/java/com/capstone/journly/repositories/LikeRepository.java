package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Like;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByGratitudeEntry(GratitudeEntry gratitudeEntry);
    List<Like> findByUser(User user);
    List<Like> findByGratitudeEntryAndUser(GratitudeEntry gratitudeEntry, User user);
}
