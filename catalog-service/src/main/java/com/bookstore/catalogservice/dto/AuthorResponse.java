package com.bookstore.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonRootName(value = "author")
@Relation(collectionRelation = "authors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse extends RepresentationModel<AuthorResponse> {

    private String id;
    private String name;

    private Set<BookResponse> books;
}
