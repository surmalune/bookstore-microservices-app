package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import com.bookstore.catalogservice.entity.Book;
import com.bookstore.catalogservice.mapper.BookResponseAssembler;
import com.bookstore.catalogservice.mapper.RequestMapper;
import com.bookstore.catalogservice.repository.BookRepository;
import com.bookstore.catalogservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: Логирование

@Service
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

        //log.info("Book {} is saved", createBookRequest.getTitle());

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
