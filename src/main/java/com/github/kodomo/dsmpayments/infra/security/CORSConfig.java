package com.github.kodomo.dsmpayments.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Value("${cors.user-origin}") private String userOrigin;

    @Value("${cors.admin-origin}") private String adminOrigin;

    @Value("${cors.booth-origin}") private String boothOrigin;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT")
                .allowedOrigins(
                        userOrigin,
                        adminOrigin,
                        boothOrigin,
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "http://localhost:3002"
                        );
    }
}
