package org.rabie.youcafeteria.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {
    private final HttpStatus status;
    public UserException(String message, HttpStatus status) {
        super("Error : "+message);
        this.status = status;
    }

}
