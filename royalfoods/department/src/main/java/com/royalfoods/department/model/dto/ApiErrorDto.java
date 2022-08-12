package com.royalfoods.department.model.dto;

import lombok.*;

import java.time.*;
import java.util.*;

@Data
public class ApiErrorDto {

    private String errorMessage;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String errorCode;

    public ApiErrorDto errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ApiErrorDto errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public static ApiErrorDto builder(){
        return new ApiErrorDto();
    }

   public  ApiErrorDto build(){
        return this;
    }

}
