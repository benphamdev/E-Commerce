//package org.example.ecommerce.application.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/places").allowedOrigins("*");
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
////        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
////        configuration.setAllowedMethods(Collections.singletonList("*"));
////        configuration.setAllowedHeaders(Collections.singletonList("*"));
////        configuration.setAllowCredentials(true);
////        configuration.setExposedHeaders(Collections.singletonList("Content-Disposition"));
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////
//        return new CorsFilter();
//    }
//}
