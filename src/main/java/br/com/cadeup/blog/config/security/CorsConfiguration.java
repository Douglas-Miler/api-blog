package br.com.cadeup.blog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cards")
            .allowedOrigins("http://localhost:4200", "https://demo-angular-blog.herokuapp.com")
            .allowedMethods("GET");
        
        registry.addMapping("/signin")
        .allowedOrigins("http://localhost:4200", "https://demo-angular-blog.herokuapp.com")
        .allowedMethods("POST");
        
        registry.addMapping("/article/{id}")
        .allowedOrigins("http://localhost:4200", "https://demo-angular-blog.herokuapp.com")
        .allowedMethods("GET");
    }
}
