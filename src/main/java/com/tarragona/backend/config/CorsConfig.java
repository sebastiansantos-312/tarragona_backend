package com.tarragona.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns(
                    "http://localhost:5173",           // Vite dev server
                    "http://localhost:3000",            // CRA (por si acaso)
                    "https://tarragona-front.vercel.app", // Vercel producción
                    "https://tarragona-front-*.vercel.app" // Vercel previews
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}