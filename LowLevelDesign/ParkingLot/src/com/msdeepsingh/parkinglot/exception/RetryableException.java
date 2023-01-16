package com.msdeepsingh.parkinglot.exception;

public class RetryableException extends RuntimeException {
    private String message;

    public RetryableException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
