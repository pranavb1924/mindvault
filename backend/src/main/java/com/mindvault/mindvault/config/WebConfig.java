package com.mindvault.mindvault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")          // every REST endpoint
                        .allowedOrigins("http://localhost:4200") // Angular dev server
                        .allowedMethods("*")            // GET, POST, PUT, DELETE, OPTIONS…
                        .allowedHeaders("*");           // Content-Type, Authorization, etc.
            }
        };
    }
}
