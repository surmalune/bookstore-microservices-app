package com.mywebapps.catalogservice.mapper;

import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.entity.Author;
import com.mywebapps.catalogservice.entity.Book;
import com.mywebapps.catalogservice.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RequestMapper {

    @Autowired
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
