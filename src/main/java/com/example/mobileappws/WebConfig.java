package com.example.mobileappws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);


//        registry.addMapping("/users/email-verification");
        registry.addMapping("/**")
                .allowedMethods("GET","POST", "PUT")
                .allowedOrigins("http://localhost:8074");


    }
}
