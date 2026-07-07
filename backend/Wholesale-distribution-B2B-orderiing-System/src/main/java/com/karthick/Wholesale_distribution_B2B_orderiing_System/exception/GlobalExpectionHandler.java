package com.karthick.Wholesale_distribution_B2B_orderiing_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
        @ExceptionHandler(MethodArgumentNotValidException.class)
                public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
                Map<String,String>errors=new HashMap<>();
                for(FieldError error:ex.getBindingResult().getFieldErrors()){
                    errors.put(error.getField(),
                    error.getDefaultMessage());
                }
                return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
}
