package com.example.apigateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user-service")
                .pathsToMatch("/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi txnApi() {
        return GroupedOpenApi.builder()
                .group("txn-service")
                .pathsToMatch("/txns/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reportApi() {
        return GroupedOpenApi.builder()
                .group("report-service")
                .pathsToMatch("/reports/**")
                .build();
    }
}
