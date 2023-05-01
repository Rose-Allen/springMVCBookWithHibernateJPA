package ru.dimash.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dimash.spring.models.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
