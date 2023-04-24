package com.journey.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义消息返回
 *
 * @author hy
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum HttpMessageEnum {

    REQUEST_SUCCESS("请求成功"),
    REQUEST_ERROR("请求失败"),
    UNKNOWN_ERROR("未知错误"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空");

    private final String message;

}