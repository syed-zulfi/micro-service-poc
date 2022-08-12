package com.royalfood.user.exception;

import com.royalfood.user.model.dto.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEntityExistException(EntityExistsException exception) {
        return ApiError
                .builder()
                .errorMessage(exception.getLocalizedMessage())
                .httStatus(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEntityNotFoundException(EntityNotFoundException exception) {
        return ApiError.builder().errorMessage(exception.getLocalizedMessage())
                .httStatus(HttpStatus.BAD_REQUEST)
                .build();
    }
}
