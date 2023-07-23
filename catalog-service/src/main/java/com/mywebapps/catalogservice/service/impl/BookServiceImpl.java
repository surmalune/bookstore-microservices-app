package com.mywebapps.catalogservice.service.impl;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.mapper.BookResponseAssembler;
import com.mywebapps.catalogservice.mapper.RequestMapper;
import com.mywebapps.catalogservice.entity.Book;
import com.mywebapps.catalogservice.repository.BookRepository;
import com.mywebapps.catalogservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: Логирование

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookResponseAssembler bookResponseAssembler;
    private final RequestMapper requestMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookResponseAssembler bookResponseAssembler,
                           RequestMapper requestMapper) {
        this.bookRepository = bookRepository;
        this.bookResponseAssembler = bookResponseAssembler;
        this.requestMapper = requestMapper;
    }

    @Override
    public Optional<BookResponse> createBook(CreateBookRequest createBookRequest) {
        Book book = requestMapper.toBook(createBookRequest);
        bookRepository.save(book);

        log.info("Book {} is saved", createBookRequest.getTitle());

        return Optional.of(bookResponseAssembler.toModel(book));
    }

    @Override
    public Optional<BookResponse> getById(String id) {
        return bookRepository.findById(id)
                             .map(bookResponseAssembler::toModel);
    }

    @Override
    public CollectionModel<BookResponse> listBooks() {
        List<Book> books = bookRepository.findAll();
        return bookResponseAssembler.toCollectionModel(books);
    }

    @Override
    public CollectionModel<BookResponse> listBooksByAuthorName(String name) {
        List<Book> books = bookRepository.findAllByAuthorsName(name);
        return bookResponseAssembler.toCollectionModel(books);
    }


}
