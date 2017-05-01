package com.bellproject.controler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(int userId) {
        super("User id Not Found " + userId );
    }
}
