package com.msdeepsingh.parkinglot.exception;

public class NonRetryableException extends RuntimeException {
    private String message;

    public NonRetryableException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
