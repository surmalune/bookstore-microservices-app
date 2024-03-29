package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import com.bookstore.catalogservice.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.NoSuchElementException;

//TODO: put, delete

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest request) {
        BookResponse response = bookService.createBook(request);

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(response.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable String id)
            throws NoSuchElementException {

        return bookService.getById(id)
                          .map(ResponseEntity::ok)
                          .orElseThrow(() ->
                                  new NoSuchElementException(String.format("Book with id '%s' not found", id)));
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable String id)
            throws NoSuchElementException {

        return bookService.getById(id)
                          .map(BookResponse::getPrice)
                          .map(ResponseEntity::ok)
                          .orElseThrow(() ->
                                  new NoSuchElementException(String.format("Book with id '%s' not found", id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> changePrice(@PathVariable String id,
                                                    @RequestBody @Min(value = 0) BigDecimal price)
            throws NoSuchElementException {

        return bookService.changePrice(id, price)
                          .map(ResponseEntity::ok)
                          .orElseThrow(() ->
                                  new NoSuchElementException(String.format("Book with id '%s' not found", id)));
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
