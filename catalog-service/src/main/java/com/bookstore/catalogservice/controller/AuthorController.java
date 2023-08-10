package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import com.bookstore.catalogservice.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

//TODO: put, delete

@RequiredArgsConstructor
@RequestMapping("/author")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest request) {

        AuthorResponse response = authorService.createAuthor(request);

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(response.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable String id)
            throws NoSuchElementException {

        return authorService.getById(id)
                            .map(ResponseEntity::ok)
                            .orElseThrow(() ->
                                    new NoSuchElementException(String.format("Author with id '%s' not found", id)));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<AuthorResponse>> listAuthors() {
        return new ResponseEntity<>(
                authorService.listAuthors(),
                HttpStatus.OK);
    }
}
