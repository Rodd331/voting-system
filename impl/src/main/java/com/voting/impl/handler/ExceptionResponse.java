package com.voting.impl.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Generated
@AllArgsConstructor
@lombok.Data
@Builder
public class ExceptionResponse {

    private String name;
    private Object cause;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
}