package com.yguo57.exception;

import com.yguo57.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.util.StringUtils;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "Failure of an operation!");
    }
}
