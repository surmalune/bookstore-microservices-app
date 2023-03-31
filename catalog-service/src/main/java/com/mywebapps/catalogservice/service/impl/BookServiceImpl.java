package com.mywebapps.catalogservice.service.impl;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.mapper.BookMapper;
import com.mywebapps.catalogservice.model.Book;
import com.mywebapps.catalogservice.repository.AuthorRepository;
import com.mywebapps.catalogservice.repository.BookRepository;
import com.mywebapps.catalogservice.service.AuthorService;
import com.mywebapps.catalogservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: Логирование везде
//TODO: Валидация ввода

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void createBook(CreateBookRequest createBookRequest) {
        Book book = BookMapper.INSTANCE.createBookRequestToBook(createBookRequest, authorService);
        bookRepository.save(book);
        log.info("Book {} is saved", book.getId());
    }

    @Override
    public BookResponse showBook(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return BookMapper.INSTANCE.bookToBookResponse(book.orElse(null)); //TODO: throw
    }

    @Override
    public List<BookResponse> listBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> listBooksByAuthorName(String name) {
        List<Book> books = bookRepository.findAllByAuthorsName(name);
        return books.stream().map(BookMapper.INSTANCE::bookToBookResponse).collect(Collectors.toList());
    }


}
