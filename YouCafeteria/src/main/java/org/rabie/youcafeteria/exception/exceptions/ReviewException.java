package org.rabie.youcafeteria.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReviewException extends RuntimeException {
    private final HttpStatus status;
    public ReviewException(String message, HttpStatus status) {
        super("Error : "+message);
        this.status = status;
    }
}
