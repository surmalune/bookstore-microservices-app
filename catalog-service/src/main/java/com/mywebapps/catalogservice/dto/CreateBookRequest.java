package com.mywebapps.catalogservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {
    @NotBlank(message = "Title cant be blank")
    @Size(min = 1, max = 100, message = "Title should be [0, 100]")
    private String title;

    private String description;

    @Min(value = 0)
    private BigDecimal price;

    @NotBlank(message = "Id cant be blank")
    private Set<String> authorIds;
}
