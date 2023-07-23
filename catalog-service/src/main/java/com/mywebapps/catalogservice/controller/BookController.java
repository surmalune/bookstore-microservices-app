package com.mywebapps.catalogservice.controller;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//TODO: исключения
//TODO: put, delete

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        Optional<BookResponse> bookResponse = bookService.createBook(createBookRequest);

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(bookResponse.orElseThrow(null).getId())
                                              .toUri();

        return ResponseEntity.created(location).body(bookResponse.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable String id) {
        return bookService.getById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<CollectionModel<BookResponse>> listBooks() {
        return new ResponseEntity<>(
                bookService.listBooks(),
                HttpStatus.OK);
    }

    // TODO: больше параметров поиска
    @GetMapping("/find")
    public ResponseEntity<CollectionModel<BookResponse>> listBooksByAuthorName(@RequestParam(name = "authorName") String name) {
        return new ResponseEntity<>(
                bookService.listBooksByAuthorName(name),
                HttpStatus.OK);
    }
}
