package com.codestates.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResponse.FieldError> customizedErrors =
                fieldErrors.stream()
                        .map(fieldError -> new ErrorResponse.FieldError(
                                fieldError.getField(),
                                fieldError.getRejectedValue(),
                                fieldError.getDefaultMessage()
                        )).collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(customizedErrors));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e) {
        return new ResponseEntity<>(
                new BusinessLogicExceptionResponse(e.getExceptionCode().getStatus(), e.getExceptionCode().getMessage()),
                HttpStatus.valueOf(e.getExceptionCode().getStatus())
        );
    }

}
