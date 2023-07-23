package com.bookstore.catalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorRequest extends RepresentationModel<CreateAuthorRequest> {

    @NotBlank(message = "Name cant be blank")
    private String name;
}
