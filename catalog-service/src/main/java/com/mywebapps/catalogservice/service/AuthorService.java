package com.mywebapps.catalogservice.service;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import org.springframework.hateoas.CollectionModel;

import java.util.Optional;

public interface AuthorService {
    Optional<AuthorResponse> createAuthor(CreateAuthorRequest createAuthorRequest);

    Optional<AuthorResponse> getById(String id);

    CollectionModel<AuthorResponse> listAuthors();
}
