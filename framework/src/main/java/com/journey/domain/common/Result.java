package com.journey.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 统一响应结果类
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("all")
@Schema(name = "统一响应模型")
public class Result implements Serializable {

    @Schema(title = "状态码")
    private Integer code;
    @Schema(title = "响应说明")
    private String message;
    @Schema(title = "响应数据")
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success() {
        return new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static Result success(Object data) {
        return new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static Result success(String message) {
        return new Result(HttpStatus.OK.value(), message);
    }

    public static Result success(String message, Object data) {
        return new Result(HttpStatus.OK.value(), message, data);
    }

    public static Result success(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result fail() {
        return new Result(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public static Result fail(Object data) {
        return new Result(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public static Result fail(String message) {
        return new Result(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static Result fail(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(String message) {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result isStatus(Integer status) {
        return status > 0 ? success() : fail();
    }
}