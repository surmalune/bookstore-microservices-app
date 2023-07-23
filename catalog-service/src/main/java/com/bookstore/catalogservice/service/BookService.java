package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import org.springframework.hateoas.CollectionModel;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface BookService {
    BookResponse createBook(CreateBookRequest createBookRequest);

    Optional<BookResponse> getById(String id);

    CollectionModel<BookResponse> listBooks();
    CollectionModel<BookResponse> listBooksByAuthorName(String name) throws NoSuchElementException;
}
