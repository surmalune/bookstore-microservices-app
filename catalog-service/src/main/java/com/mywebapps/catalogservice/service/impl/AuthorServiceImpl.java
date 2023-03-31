package com.mywebapps.catalogservice.service.impl;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.mapper.AuthorMapper;
import com.mywebapps.catalogservice.model.Author;
import com.mywebapps.catalogservice.repository.AuthorRepository;
import com.mywebapps.catalogservice.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public  void createAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = AuthorMapper.INSTANCE.createAuthorRequestToAuthor(createAuthorRequest);
        authorRepository.save(author);
        log.info("Author named {} with id {} is added", author.getName(), author.getId());
    }

    @Override
    public List<AuthorResponse> listAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(AuthorMapper.INSTANCE::authorToAuthorResponse).collect(Collectors.toList());
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElse(null);
    }
}
