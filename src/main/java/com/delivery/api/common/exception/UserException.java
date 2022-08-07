package com.delivery.api.common.exception;

public class UserException extends Exception {

    public static class PasswordNotValidException extends Exception {
        public PasswordNotValidException(String message) {
            super(message);
        }
    }
    public static class LoginFailException extends Exception {
        public LoginFailException(String message) {
            super(message);
        }
    }
    public static class AuthException extends Exception {
        public AuthException(String message) {
            super(message);
        }
    }
}
