package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import com.bookstore.catalogservice.entity.Author;
import com.bookstore.catalogservice.service.AuthorService;
import com.bookstore.catalogservice.mapper.AuthorResponseAssembler;
import com.bookstore.catalogservice.mapper.RequestMapper;
import com.bookstore.catalogservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorResponseAssembler authorResponseAssembler;
    private final RequestMapper requestMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorResponseAssembler authorResponseAssembler,
                             RequestMapper requestMapper) {
        this.authorRepository = authorRepository;
        this.authorResponseAssembler = authorResponseAssembler;
        this.requestMapper = requestMapper;
    }

    @Override
    public Optional<AuthorResponse> createAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = requestMapper.toAuthor(createAuthorRequest);
        authorRepository.save(author);

        //log.info("Author named {} is added", createAuthorRequest.getName());

        return Optional.of(authorResponseAssembler.toModel(author));
    }

    @Override
    public CollectionModel<AuthorResponse> listAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorResponseAssembler.toCollectionModel(authors);
    }

    @Override
    public Optional<AuthorResponse> getById(String id) {
        return authorRepository.findById(id)
                               .map(authorResponseAssembler::toModel);
    }
}
