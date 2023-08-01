package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import com.bookstore.catalogservice.entity.Author;
import com.bookstore.catalogservice.mapper.AuthorResponseAssembler;
import com.bookstore.catalogservice.mapper.RequestMapper;
import com.bookstore.catalogservice.repository.AuthorRepository;
import com.bookstore.catalogservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorResponseAssembler authorResponseAssembler;
    private final RequestMapper requestMapper;

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        Author author = requestMapper.toAuthor(request);
        authorRepository.save(author);

        log.info("Author with name '{}' added", request.getName());

        return authorResponseAssembler.toModel(author);
    }

    // TODO: should i return empty list or throw exception?
    @Override
    public CollectionModel<AuthorResponse> listAuthors()
    {
        List<Author> authors = authorRepository.findAll();
        return authorResponseAssembler.toCollectionModel(authors);
    }

    @Override
    public Optional<AuthorResponse> getById(String id) {
        return authorRepository.findById(id)
                               .map(authorResponseAssembler::toModel);
    }
}
