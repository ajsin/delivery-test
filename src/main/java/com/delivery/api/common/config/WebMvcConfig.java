package com.delivery.api.common.config;

import com.delivery.api.common.interceptor.SignInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    SignInInterceptor signInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInInterceptor)
                .excludePathPatterns("/v3/api-docs")
                .excludePathPatterns("/v3/api-docs/*")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**/")
                .excludePathPatterns("/error")
        ;
    }
}
