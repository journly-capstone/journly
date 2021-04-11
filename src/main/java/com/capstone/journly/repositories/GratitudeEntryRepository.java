package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GratitudeEntryRepository extends JpaRepository<GratitudeEntry, Long> {
        List<GratitudeEntry> findAll();
        GratitudeEntry findGratitudeEntriesById(long id);

        @Query(value = "SELECT * FROM gratitude_entry", nativeQuery = true)
        public List<GratitudeEntry> getAllEntries();
}
