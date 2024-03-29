package com.bookstore.catalogservice.mapper;

import com.bookstore.catalogservice.controller.AuthorController;
import com.bookstore.catalogservice.controller.BookController;
import com.bookstore.catalogservice.dto.AuthorResponse;
import com.bookstore.catalogservice.dto.BookResponse;
import com.bookstore.catalogservice.entity.Author;
import com.bookstore.catalogservice.entity.Book;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorResponseAssembler extends RepresentationModelAssemblerSupport<Author, AuthorResponse>
{
    public AuthorResponseAssembler() {
        super(AuthorController.class, AuthorResponse.class);
    }

    @Override
    public AuthorResponse toModel(Author entity) {
        AuthorResponse authorResponse = instantiateModel(entity);

        authorResponse.add(linkTo(
                methodOn(AuthorController.class)
                        .getAuthor(entity.getId()))
                .withSelfRel());

        authorResponse.setId(entity.getId());
        authorResponse.setName(entity.getName());
        authorResponse.setBooks(toBookResponse(entity.getBooks()));
        return authorResponse;
    }

    @Override
    public CollectionModel<AuthorResponse> toCollectionModel(Iterable<? extends Author> entities)
    {
        CollectionModel<AuthorResponse> authorResponses = super.toCollectionModel(entities);
        authorResponses.add(linkTo(methodOn(AuthorController.class).listAuthors()).withSelfRel());
        return authorResponses;
    }

    private Set<BookResponse> toBookResponse(Set<Book> books) {
        if (books == null || books.isEmpty())
            return Collections.emptySet();

        return books.stream()
                    .map(book -> BookResponse.builder()
                                             .id(book.getId())
                                             .title(book.getTitle())
                                             .description(book.getDescription())
                                             .price(book.getPrice())
                                             .build()
                                             .add(WebMvcLinkBuilder.linkTo(
                                                     methodOn(BookController.class)
                                                             .getBook(book.getId()))
                                                                   .withSelfRel()))
                    .collect(Collectors.toSet());
    }
}
