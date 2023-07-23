package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import com.bookstore.catalogservice.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

//TODO: исключения
//TODO: put, delete

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest createAuthorRequest) {

        AuthorResponse authorResponse = authorService.createAuthor(createAuthorRequest);

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(authorResponse.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(authorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable String id)
            throws NoSuchElementException {

        return authorService.getById(id)
                            .map(ResponseEntity::ok)
                            .orElseThrow(() -> new NoSuchElementException("author not found"));
    }

    // TODO: if list is empty, should i return not_found or just return empty list?
    @GetMapping
    public ResponseEntity<CollectionModel<AuthorResponse>> listAuthors() {
        return new ResponseEntity<>(
                authorService.listAuthors(),
                HttpStatus.OK);
    }
}
