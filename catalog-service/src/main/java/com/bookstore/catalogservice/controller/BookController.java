package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import com.bookstore.catalogservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

//TODO: исключения
//TODO: put, delete


@RequiredArgsConstructor
@RequestMapping("/api/book")
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        BookResponse bookResponse = bookService.createBook(createBookRequest);

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(bookResponse.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable String id) {
        return bookService.getById(id)
                          .map(ResponseEntity::ok)
                          .orElseThrow(() -> new NoSuchElementException("Book not found"));

    }

    @GetMapping
    public ResponseEntity<CollectionModel<BookResponse>> listBooks() {
        return new ResponseEntity<>(
                bookService.listBooks(),
                HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<CollectionModel<BookResponse>> listBooksByAuthorName(@RequestParam(name = "authorName") String name)
            throws NoSuchElementException {

        return new ResponseEntity<>(
                bookService.listBooksByAuthorName(name),
                HttpStatus.OK);
    }
}
