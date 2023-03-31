package com.mywebapps.catalogservice.controller;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: использовать хаетос
//TODO: класс исключений

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody @Valid CreateAuthorRequest createAuthorRequest) {
        authorService.createAuthor(createAuthorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponse> listAuthors() {
        return authorService.listAuthors();
    }
}
