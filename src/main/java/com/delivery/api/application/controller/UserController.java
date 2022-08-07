package com.delivery.api.application.controller;

import com.delivery.api.application.dto.UserSignInReqDTO;
import com.delivery.api.application.dto.UserSignInResDTO;
import com.delivery.api.application.dto.UserSignUpReqDTO;
import com.delivery.api.application.service.UserService;
import com.delivery.api.common.annotation.NoSignHandler;
import com.delivery.api.common.model.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@Tag(name = "1. User관련 API")
public class UserController {

    @Autowired
    UserService userService;

    @NoSignHandler
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 API", description = "회원가입시 호출되는 API")
    public CommonResponse signUp(@RequestBody @Valid UserSignUpReqDTO userSignUpReqDTO) throws Exception {

        userService.signUp(userSignUpReqDTO);

        return CommonResponse.builder()
                .build();
    }

    @NoSignHandler
    @PostMapping("/sign-in")
    @Operation(summary = "로그인 API", description = "로그인시 호출되는 API")
    public CommonResponse<UserSignInResDTO> signIn(@RequestBody @Valid UserSignInReqDTO userSignInReqDTO) throws Exception {

        UserSignInResDTO userSignInResDTO = userService.signIn(userSignInReqDTO);

        return CommonResponse.<UserSignInResDTO>builder()
                .data(userSignInResDTO)
                .build();
    }

}
