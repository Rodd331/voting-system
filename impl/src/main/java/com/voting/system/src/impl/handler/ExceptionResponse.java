package com.voting.system.src.impl.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class ExceptionResponse {

    private String name;
    private Object cause;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
}
