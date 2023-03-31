package com.mywebapps.catalogservice.mapper;

import com.mywebapps.catalogservice.dto.BookResponse;
import com.mywebapps.catalogservice.dto.CreateBookRequest;
import com.mywebapps.catalogservice.model.Book;
import com.mywebapps.catalogservice.service.AuthorService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { AuthorService.class })
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "authors", expression = "java(createBookRequest.getAuthorIds().stream().map(b -> authorService.findById(b)).collect(java.util.stream.Collectors.toSet()))")
    Book createBookRequestToBook(CreateBookRequest createBookRequest, @Context AuthorService authorService);

    BookResponse bookToBookResponse(Book book);
    Book bookResponseToBook(BookResponse bookResponse);
}
