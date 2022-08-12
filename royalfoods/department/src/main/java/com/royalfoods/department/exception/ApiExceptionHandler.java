package com.royalfoods.department.exception;

import com.royalfoods.department.model.dto.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDto handleEntityNotFoundException(EntityNotFoundException exception) {
        ApiErrorDto errorDto = ApiErrorDto.builder()
                                .errorMessage(exception.getLocalizedMessage())
                                .build();
        return errorDto;
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDto handleEntityExistsException(EntityExistsException exception) {
       return ApiErrorDto.builder().errorMessage(exception.getLocalizedMessage()).build();
    }
}
