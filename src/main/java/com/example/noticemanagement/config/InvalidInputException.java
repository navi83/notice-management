package com.example.noticemanagement.config;

public class InvalidInputException extends RuntimeException {

    private String paramName;
    private String message;

    public InvalidInputException(String paramName, String message) {
        super("\"" + paramName + "\" provided is invalid. It should be " + message);
        this.paramName = paramName;
        this.message = message;
    }
}