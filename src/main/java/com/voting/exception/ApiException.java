package com.voting.exception;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Generated
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
}