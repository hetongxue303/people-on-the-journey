package com.journey.annotation;

import java.lang.annotation.*;

/**
 * 打印日志注解
 *
 * @author hy
 * @version 1.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
    String value() default "";
}