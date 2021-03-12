package com.capstone.journly.repositories;

import com.capstone.journly.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>  {
}
