package com.delivery.api;

import com.delivery.api.application.dto.UserSignUpReqDTO;
import com.delivery.api.application.service.impl.UserServiceImpl;
import com.delivery.api.common.exception.UserException;
import com.delivery.api.domain.user.repository.orm.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Nested
    @DisplayName("회원가입")
    class SignUp {

        @Nested
        @DisplayName("정상 케이스")
        class Success {

            @Test
            @DisplayName("성공")
            void signUpSuccess() throws Exception {

                UserSignUpReqDTO userSignUpReqDTO = new UserSignUpReqDTO("ajsin", "Abcdqewr12345", "smh");
                userService.signUp(userSignUpReqDTO);

            }

        }

        @Nested
        @DisplayName("실패 케이스")
        class Fail {

            @Test
            @DisplayName("패스워드 조건 잘못된 경우")
            void wrongPassword() throws Exception {

                UserSignUpReqDTO userSignUpReqDTO = new UserSignUpReqDTO("ajsin", "abcdqewr12345", "ssmm");

                assertThatThrownBy(() -> userService.signUp(userSignUpReqDTO)).isInstanceOf(UserException.PasswordNotValidException.class);

            }
        }

    }
}
