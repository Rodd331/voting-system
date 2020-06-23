package com.voting.system.src.impl.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;
}
