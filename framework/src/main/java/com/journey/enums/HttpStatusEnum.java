package com.journey.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义状态码返回
 *
 * @author hy
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum {
    UNKNOWN_EXCEPTION(5000, "未知异常");

    private final Integer code;
    private final String message;
}