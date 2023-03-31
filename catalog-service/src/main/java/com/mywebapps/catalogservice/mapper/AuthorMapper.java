package com.mywebapps.catalogservice.mapper;

import com.mywebapps.catalogservice.dto.AuthorResponse;
import com.mywebapps.catalogservice.dto.CreateAuthorRequest;
import com.mywebapps.catalogservice.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorResponse authorToAuthorResponse(Author author);
    Author authorResponseToAuthor(AuthorResponse authorResponse);
    Author createAuthorRequestToAuthor(CreateAuthorRequest createAuthorRequest);
}
