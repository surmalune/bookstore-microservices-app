package com.mywebapps.catalogservice.mapper;

import com.mywebapps.catalogservice.controller.AuthorController;
import com.mywebapps.catalogservice.controller.BookController;
import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.entity.Author;
import com.mywebapps.catalogservice.entity.Book;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookResponseAssembler extends RepresentationModelAssemblerSupport<Book, BookResponse> {

    public BookResponseAssembler() {
        super(BookController.class, BookResponse.class);
    }

    @Override
    public BookResponse toModel(Book entity) {
        BookResponse bookResponse = instantiateModel(entity);

        bookResponse.add(linkTo(
                methodOn(BookController.class)
                        .getBook(entity.getId()))
                .withSelfRel());

        bookResponse.setId(entity.getId());
        bookResponse.setTitle(entity.getTitle());
        bookResponse.setDescription(entity.getDescription());
        bookResponse.setPrice(entity.getPrice());
        bookResponse.setAuthors(toAuthorResponse(entity.getAuthors()));
        return bookResponse;
    }

    @Override
    public CollectionModel<BookResponse> toCollectionModel(Iterable<? extends Book> entities)
    {
        CollectionModel<BookResponse> bookResponses = super.toCollectionModel(entities);
        bookResponses.add(linkTo(methodOn(BookController.class).listBooks()).withSelfRel());
        return bookResponses;
    }

    private Set<AuthorResponse> toAuthorResponse(Set<Author> authors) {
        if (authors.isEmpty())
            return Collections.emptySet();

        return authors.stream()
                    .map(author -> AuthorResponse.builder()
                                             .id(author.getId())
                            .name(author.getName())
                            .build()
                                             .add(linkTo(
                                                     methodOn(AuthorController.class)
                                                             .getAuthor(author.getId()))
                                                     .withSelfRel()))
                    .collect(Collectors.toSet());
    }
}
