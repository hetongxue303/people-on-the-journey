package com.journey.handler.exception.customs;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统异常类
 *
 * @author hy
 * @version 1.0
 */
@Getter
@Setter
public class SystemException extends RuntimeException {

    private Integer code;
    private String message;

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public SystemException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

}