package com.mywebapps.catalogservice.service.impl;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.mapper.AuthorResponseAssembler;
import com.mywebapps.catalogservice.mapper.RequestMapper;
import com.mywebapps.catalogservice.entity.Author;
import com.mywebapps.catalogservice.repository.AuthorRepository;
import com.mywebapps.catalogservice.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

        log.info("Author named {} is added", createAuthorRequest.getName());

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
