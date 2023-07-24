package com.bookstore.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticateUserResponse implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;
}
