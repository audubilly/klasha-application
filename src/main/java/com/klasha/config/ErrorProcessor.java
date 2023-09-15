package com.klasha.config;

import com.klasha.dto.response.ApiResponse;
import com.klasha.exceptions.BadRequestException;
import com.klasha.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorProcessor {


    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse badRequestExceptionHandler(BadRequestException ex) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse exceptionHandler(Exception ex) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }
    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse misMatchErrorHandler(ChangeSetPersister.NotFoundException ex) {
        log.info("throwing this::::::::::::: {}", ex.getMessage());
        return new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.info("Method Argument not valid throwing....");

        List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), errorList.get(0));
    }


}
