package org.rabie.youcafeteria.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DishException extends RuntimeException {
    private final HttpStatus status;
    public DishException(String message, HttpStatus status) {
            super("Error : "+message);
            this.status = status;
    }
}
