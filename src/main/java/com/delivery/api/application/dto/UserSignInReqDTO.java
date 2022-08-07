package com.delivery.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@Schema(description = "로그인 request 객체")
public class UserSignInReqDTO {

    @Schema(description = "아이디")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @Schema(description = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;
}
