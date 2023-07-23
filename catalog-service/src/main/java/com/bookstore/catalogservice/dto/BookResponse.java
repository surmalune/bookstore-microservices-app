package com.bookstore.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonRootName(value = "book")
@Relation(collectionRelation = "books")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse extends RepresentationModel<BookResponse> {

    private String id;
    private String title;
    private String description;
    private BigDecimal price;

    private Set<AuthorResponse> authors;
}
