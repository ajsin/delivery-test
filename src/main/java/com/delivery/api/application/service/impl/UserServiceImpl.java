package com.delivery.api.application.service.impl;

import com.delivery.api.application.dto.UserSignInReqDTO;
import com.delivery.api.application.dto.UserSignInResDTO;
import com.delivery.api.application.dto.UserSignUpReqDTO;
import com.delivery.api.application.service.UserService;
import com.delivery.api.common.exception.UserException;
import com.delivery.api.common.util.CommonUtil;
import com.delivery.api.common.util.JwtTokenUtil;
import com.delivery.api.domain.user.model.entity.User;
import com.delivery.api.domain.user.repository.orm.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void signUp(UserSignUpReqDTO userSignUpReqDTO) throws Exception {

        if(!CommonUtil.validatePw(userSignUpReqDTO.getPw())) {
            throw new UserException.PasswordNotValidException("비밀번호 조건이 맞지 않습니다.(영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로 12자리 이상의 문자열)");
        }

        User user = new User(
                CommonUtil.createUid(),
                userSignUpReqDTO.getId(),
                userSignUpReqDTO.getName(),
                CommonUtil.encryptPw(userSignUpReqDTO.getPw())
        );

        userRepository.save(user);
    }

    @Override
    public UserSignInResDTO signIn(UserSignInReqDTO userSignInReqDTO) throws Exception {

        User user = userRepository.findByUserIdAndPassword(
                userSignInReqDTO.getId(),
                CommonUtil.encryptPw(userSignInReqDTO.getPw())
                )
                .orElseThrow(()-> new UserException.LoginFailException("로그인 정보가 일치하지 않습니다. 아이디 혹은 비밀번호를 다시 확인해주세요."));

        log.info("user : " + user.getUserId());

        return new UserSignInResDTO(jwtTokenUtil.createToken(user.getUid()));
    }
}
