package com.voting.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Generated
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {

    private String name;
    private Object cause;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
}