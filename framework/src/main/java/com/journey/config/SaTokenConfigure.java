package com.journey.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * SaTokenConfigure
 *
 * @author hy
 * @version 1.0
 */
@EnableWebMvc
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls = new ArrayList<>();
        urls.add("/admin/login");

        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 登录认证 -- 拦截所有路由，并排除登录接口 用于开放登录
                    SaRouter.match("/**").notMatch(urls).check(r -> StpUtil.checkLogin());
                }).isAnnotation(true))
                // 拦截所有接口
                .addPathPatterns("/**")
                // 不拦截的接口
                .excludePathPatterns(urls);
    }

}