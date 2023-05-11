package com.codestates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessLogicExceptionResponse {

    int status;

    String message;

}
