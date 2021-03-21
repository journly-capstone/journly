package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GratitudeEntryRepository extends JpaRepository<GratitudeEntry, Long> {
        List<GratitudeEntry> findAll();
//        List<GratitudeEntry> findByUser(User user);

}
