package com.syf.chat.common.config;

import com.syf.chat.common.advice.HttpHeaderFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FilterConfiguration {
    /**
     * 配置自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<HttpHeaderFilter> registrationBean(){
        log.info("注册过滤器----------");
        FilterRegistrationBean<HttpHeaderFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpHeaderFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpHeaderFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }
}
