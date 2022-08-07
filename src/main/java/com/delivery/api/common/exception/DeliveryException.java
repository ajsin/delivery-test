package com.delivery.api.common.exception;

public class DeliveryException extends Exception {

    public static class WrongPeriodException extends Exception {
        public WrongPeriodException(String message) {
            super(message);
        }
    }

    public static class NotModifiableException extends Exception {
        public NotModifiableException(String message) {
            super(message);
        }
    }
}
