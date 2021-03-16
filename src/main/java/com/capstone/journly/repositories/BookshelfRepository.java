package com.capstone.journly.repositories;

import com.capstone.journly.models.Bookshelf;
import com.capstone.journly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
    Bookshelf findByUser(User user);

}
