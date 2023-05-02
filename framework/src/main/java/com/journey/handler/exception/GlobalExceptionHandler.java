package com.journey.handler.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.journey.domain.common.Result;
import com.journey.handler.exception.customs.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author hy
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public Result handlerSystemException(SystemException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error(Objects.isNull(e.getCode()) ? HttpStatus.INTERNAL_SERVER_ERROR.value() : e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public Result handlerSystemException(NotLoginException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

}