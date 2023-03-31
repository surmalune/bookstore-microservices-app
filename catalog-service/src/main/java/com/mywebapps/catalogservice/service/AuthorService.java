package com.mywebapps.catalogservice.service;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.model.Author;

import java.util.List;

public interface AuthorService {
    void createAuthor(CreateAuthorRequest createAuthorRequest);
    List<AuthorResponse> listAuthors();
    Author findById(String id);
}
