package com.syf.chat.common.config;

import com.syf.chat.common.advice.MyHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义 MVC
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 配置跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有接口都开放跨域
                .allowedOrigins("http://localhost:3000", "http://example.com") // 允许的域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true) // 允许携带凭证（如 Cookies）
                .maxAge(3600); // 预检请求的缓存时间，单位为秒
    }
}
