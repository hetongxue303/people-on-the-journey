package com.journey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;

/**
 * 跨越配置
 *
 * @author hy
 * @version 1.0
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许跨域的源(spring boot 2.4.x及以上版本需要这样配置)
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("*");
        configuration.setAllowedOriginPatterns(allowedOriginPatterns);
        // 允许跨域的头
        configuration.addAllowedHeader("*");
        // 允许跨域的请求方法
        configuration.addAllowedMethod("*");
        // 是否跨域发送cookie
        configuration.setAllowCredentials(true);
        // 设置最长期限
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}