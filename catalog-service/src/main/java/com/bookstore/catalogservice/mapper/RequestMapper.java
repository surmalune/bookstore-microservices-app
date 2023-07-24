package com.bookstore.catalogservice.mapper;

import com.bookstore.catalogservice.dto.CreateAuthorRequest;
import com.bookstore.catalogservice.dto.CreateBookRequest;
import com.bookstore.catalogservice.entity.Author;
import com.bookstore.catalogservice.entity.Book;
import com.bookstore.catalogservice.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@RequiredArgsConstructor
public abstract class RequestMapper {

    protected AuthorRepository authorRepository;

    public abstract Author toAuthor(CreateAuthorRequest createAuthorRequest);

    public Book toBook(CreateBookRequest createBookRequest) {
        Book book = new Book();

        book.setTitle(createBookRequest.getTitle());
        book.setDescription(createBookRequest.getDescription());
        book.setPrice(createBookRequest.getPrice());

        Set<Author> authors = createBookRequest.getAuthorIds().stream()
                                               .map(id-> authorRepository.findById(id)
                                                                         .orElseThrow(IllegalArgumentException::new))
                                               .collect(Collectors.toSet());

        book.setAuthors(authors);
        return book;
    }
}
