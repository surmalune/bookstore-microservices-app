package com.bookstore.catalogservice.service;

import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import org.springframework.hateoas.CollectionModel;

import java.util.Optional;

public interface AuthorService {
    AuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest);

    Optional<AuthorResponse> getById(String id);

    CollectionModel<AuthorResponse> listAuthors();
}
