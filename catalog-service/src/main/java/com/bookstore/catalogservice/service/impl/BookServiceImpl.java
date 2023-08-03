package com.bookstore.catalogservice.service.impl;

import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import com.bookstore.catalogservice.entity.Book;
import com.bookstore.catalogservice.exception.AuthorNotFoundException;
import com.bookstore.catalogservice.mapper.BookResponseAssembler;
import com.bookstore.catalogservice.mapper.RequestMapper;
import com.bookstore.catalogservice.repository.BookRepository;
import com.bookstore.catalogservice.service.AuthorService;
import com.bookstore.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookResponseAssembler bookResponseAssembler;
    private final RequestMapper requestMapper;
    private final AuthorService authorService;

    @Override
    public BookResponse createBook(CreateBookRequest request)
            throws AuthorNotFoundException {

        request.getAuthorIds()
               .forEach(id -> authorService.getById(id)
                                           .orElseThrow(() ->
                                                   new AuthorNotFoundException(String.format(
                                                           "Author with id '%s' not found", id)
                                                   )));

        Book book = requestMapper.toBook(request);
        bookRepository.save(book);

        log.info("Book {} saved", request.getTitle());

        return bookResponseAssembler.toModel(book);
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
    public CollectionModel<BookResponse> listBooksByAuthorName(String name)
            throws NoSuchElementException {

        List<Book> books = bookRepository.findAllByAuthorsName(name);

        if (books.isEmpty())
            throw new NoSuchElementException(String.format("No books with author '%s'", name));

        return bookResponseAssembler.toCollectionModel(books);
    }


}
