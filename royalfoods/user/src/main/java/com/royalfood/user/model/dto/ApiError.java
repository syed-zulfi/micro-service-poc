package com.royalfood.user.model.dto;

import lombok.*;
import org.springframework.http.*;

import java.time.*;

@Data
public class ApiError {
    private String errorMessage;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp = LocalDateTime.now();

    public static ApiError builder(){
        return new ApiError();
    }
    public ApiError errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
    public ApiError httStatus(HttpStatus status) {
        this.httpStatus = status;
        return this;
    }

    public ApiError build(){
        return this;
    }
}
