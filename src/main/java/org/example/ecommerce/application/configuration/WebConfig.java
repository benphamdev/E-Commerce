package org.example.ecommerce.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

public class WebConfig {
    private static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    private static final String[] ALLOWED_ORIGINS =
            {"http://localhost:3000", "http://localhost:8081"};

    private static final String EXPOSED_HEADERS = "Authorization";

    @Bean
    CorsWebFilter corsFilter() {
//    https://docs.spring.io/spring-framework/reference/web/webflux-cors.html#webflux-cors-webfilter

        CorsConfiguration config = new CorsConfiguration();

        // Possibly...
        // config.applyPermitDefaultValues()

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGINS));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of(EXPOSED_HEADERS));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}
