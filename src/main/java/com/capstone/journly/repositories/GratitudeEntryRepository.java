package com.capstone.journly.repositories;

import com.capstone.journly.models.GratitudeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GratitudeEntryRepository extends JpaRepository<GratitudeEntry, Long> {
        GratitudeEntry findAll(long id);

}
