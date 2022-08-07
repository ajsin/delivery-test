package com.delivery.api.common.exception;

import com.delivery.api.common.code.CommonCode;
import com.delivery.api.common.code.DeliveryErrorCode;
import com.delivery.api.common.code.UserErrorCode;
import com.delivery.api.common.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice extends Throwable {

    @ExceptionHandler({UserException.PasswordNotValidException.class})
    public CommonResponse PasswordNotValidException(Exception ex) {

        return CommonResponse.builder()
                .code(UserErrorCode.USER_ERROR_PASSWORD_NOT_VALID)
                .msg(ex.getMessage())
                .build();
    }

    @ExceptionHandler({UserException.LoginFailException.class})
    public CommonResponse LoginFailException(Exception ex) {

        return CommonResponse.builder()
                .code(UserErrorCode.USER_ERROR_LOGIN_FAIL)
                .msg(ex.getMessage())
                .build();
    }

    @ExceptionHandler({UserException.AuthException.class})
    public CommonResponse AuthException(Exception ex) {

        return CommonResponse.builder()
                .code(UserErrorCode.USER_ERROR_AUTH)
                .msg(ex.getMessage())
                .build();
    }

    @ExceptionHandler({DeliveryException.WrongPeriodException.class})
    public CommonResponse WrongPeriodException(Exception ex) {

        return CommonResponse.builder()
                .code(DeliveryErrorCode.DELIVERY_ERROR_WRONG_PERIOD)
                .msg(ex.getMessage())
                .build();
    }

    @ExceptionHandler({DeliveryException.NotModifiableException.class})
    public CommonResponse NotModifiableException(Exception ex) {

        return CommonResponse.builder()
                .code(DeliveryErrorCode.DELIVERY_ERROR_NOT_MODIFIABLE)
                .msg(ex.getMessage())
                .build();
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        ex.printStackTrace();
        return CommonResponse.builder()
                .code(CommonCode.COMMON_ERROR_PARAM_CODE)
                .msg(ex.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage())
                .build();
    }


    @ExceptionHandler({Exception.class})
    public CommonResponse exception(Exception ex) {
//        ex.printStackTrace();
        return CommonResponse.builder()
                .code(CommonCode.COMMON_ERROR_CODE)
                .msg(ex.getMessage())
                .build();
    }

}
