package com.project.ams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TrailingSlashInterceptor trailingSlashInterceptor;

    // intercept when "/" is found at the end of url sent
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(trailingSlashInterceptor);
    }

    // auto redirect from "/" to "/employee"
    @Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/employee");
    }
}