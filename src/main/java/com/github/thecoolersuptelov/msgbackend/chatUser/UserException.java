package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
