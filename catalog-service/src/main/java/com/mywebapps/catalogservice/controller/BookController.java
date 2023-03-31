package com.mywebapps.catalogservice.controller;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        bookService.createBook(createBookRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse showBook(@PathVariable String id) {
        return bookService.showBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> listBooks() {
        return bookService.listBooks();
    }

    //TODO: вставить больше параметров поиска
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> listBooksByAuthorName(@RequestParam(name = "authorName") String name) {
        return bookService.listBooksByAuthorName(name);
    }
}
