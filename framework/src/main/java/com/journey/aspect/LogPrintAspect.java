package com.journey.aspect;

import com.alibaba.fastjson2.JSON;
import com.journey.annotation.LogPrint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 打印日志切面
 *
 * @author hy
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class LogPrintAspect {

    @Pointcut("@annotation(com.journey.annotation.LogPrint)")
    public void logPrint() {
    }

    @Around("logPrint()")
    public Object handlerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        LogPrint logging = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LogPrint.class);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("===============================begin===============================");
        log.info("接口描述         : {}", logging.value());
        log.info("请求方法         : {}", request.getMethod());
        log.info("请求主机         : {}", request.getRemoteHost());
        log.info("接口地址         : {}", request.getRequestURL());
        log.info("接口类名         : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature()
                .getName());
        log.info("请求参数         : {}", JSON.toJSONString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("响应数据         : {}", JSON.toJSONString(result));
        log.info("请求耗时         : {} ms", System.currentTimeMillis() - beginTime);
        log.info("================================end================================");
        return result;
    }

}