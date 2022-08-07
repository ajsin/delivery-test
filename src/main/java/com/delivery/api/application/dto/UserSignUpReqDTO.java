package com.delivery.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Schema(description = "회원가입 request 객체")
public class UserSignUpReqDTO {

    @Schema(description = "아이디")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @Schema(description = "비밀번호(영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로 12자리 이상의 문자열)")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;

    @Schema(description = "이름")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

}
