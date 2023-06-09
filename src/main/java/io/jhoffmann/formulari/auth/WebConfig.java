package io.jhoffmann.formulari.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200", "http://localhost:8080",
                        "https://formulari-frontend.herokuapp.com")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Set-Cookie",
                                "Authorization")
                        .allowCredentials(true)
                        .allowedMethods(HttpMethod.OPTIONS.name(), HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name());
            }
        };
    }
}