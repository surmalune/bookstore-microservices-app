package com.mywebapps.catalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorRequest {

    @NotBlank(message = "Name cant be blank")
    private String name;
}
