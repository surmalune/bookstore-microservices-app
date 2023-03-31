package com.mywebapps.catalogservice.service;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;

import java.util.List;

public interface BookService {
    void createBook(CreateBookRequest createBookRequest);
    List<BookResponse> listBooks();
    List<BookResponse> listBooksByAuthorName(String name);
    BookResponse showBook(String id);
}
