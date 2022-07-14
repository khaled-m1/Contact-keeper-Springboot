package com.example.contactkeeper.advise;

import com.example.contactkeeper.DTO.Api;
import com.example.contactkeeper.exceptions.ApiException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControlAdvise {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api> MethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        String message = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Api> handleDataIntegrity(DataIntegrityViolationException dataIntegrityViolationException){
        String message = dataIntegrityViolationException.getRootCause().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api> apiException(ApiException apiException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(apiException.getMessage(),400));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Api> httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(error.getMessage(),400));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api> exception(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error !",500));
    }

}
