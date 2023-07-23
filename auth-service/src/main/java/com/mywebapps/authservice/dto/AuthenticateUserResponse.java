package com.mywebapps.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticateUserResponse {

    @JsonProperty("access_token")
    private String accessToken;
}
