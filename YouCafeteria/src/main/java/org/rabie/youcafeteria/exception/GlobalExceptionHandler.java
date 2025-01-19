package org.rabie.youcafeteria.exception;

import org.rabie.youcafeteria.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity<String> handleStockException(StockException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<String> handleReservationException(ReservationException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<String> handleReviewException(ReviewException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
    @ExceptionHandler(DishException.class)
    public ResponseEntity<String> handleDishException(DishException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MenuException.class)
    public ResponseEntity<String> handleMenuException(MenuException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            if(!errors.containsKey(field)) {
                errors.put(field, List.of(Objects.requireNonNull(error.getDefaultMessage())));
            } else {
                errors.get(field).add(error.getDefaultMessage());
            }
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
