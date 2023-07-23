package com.bookstore.catalogservice.repository;

import com.bookstore.catalogservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
//    List<Book> findBooksByTitle(String title);
    List<Book> findAllByAuthorsName(String name);
}
