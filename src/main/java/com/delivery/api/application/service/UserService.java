package com.delivery.api.application.service;

import com.delivery.api.application.dto.UserSignInReqDTO;
import com.delivery.api.application.dto.UserSignInResDTO;
import com.delivery.api.application.dto.UserSignUpReqDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void signUp(UserSignUpReqDTO userSignUpReqDTO) throws Exception;
    public UserSignInResDTO signIn(UserSignInReqDTO userSignInReqDTO) throws Exception;
}
