package com.delivery.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "로그인 response 객체")
public class UserSignInResDTO {

    @Schema(description = "Access Token")
    private String accessToken;
}
