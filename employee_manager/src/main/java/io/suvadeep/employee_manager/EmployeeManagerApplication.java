package io.suvadeep.employee_manager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EmployeeManagerApplication {

    private static final String APP_URL = "http://localhost:4200";

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagerApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of(APP_URL));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Accept", "Access-Control-Allow-Origin", "Access-Control-Request-Headers", "Access-Control-Request-Method", "Authorization", "Content-Type", "Origin", "Origin, Accept", "X-Requested-With"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Accept", "Access-Control-Allow-Credentials", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Authorization", "Content-Type", "Origin"));
        corsConfiguration.setAllowedMethods(Arrays.asList("DELETE", "GET", "OPTIONS", "POST", "PUT"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
