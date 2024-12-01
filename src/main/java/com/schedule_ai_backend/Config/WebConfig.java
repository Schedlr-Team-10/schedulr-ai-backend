package com.schedule_ai_backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/schedlrai/generate")
                .allowedOrigins("http://52.64.225.94:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}