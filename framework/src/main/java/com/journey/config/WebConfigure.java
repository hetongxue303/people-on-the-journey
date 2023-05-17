package com.journey.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webConfigure
 *
 * @author hy
 * @version 1.0
 */
@EnableWebMvc
@Configuration
public class WebConfigure implements WebMvcConfigurer {
    private final String[] intercepts = {"/**"};
    private final String[] excludes = {"/admin/login", "/file/upload/**", "/user/login", "/user/register", "/share/list/home"};


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 登录认证 -- 拦截所有路由，并排除登录接口 用于开放登录
                    SaRouter.match(intercepts).notMatch(excludes).check(r -> StpUtil.checkLogin());
                }).isAnnotation(true))
                // 拦截所有接口
                .addPathPatterns(intercepts)
                // 不拦截的接口
                .excludePathPatterns(excludes);
    }

}
