package com.nexus.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI nexusOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("Nexus User Service API")
                    .version("0.1.0")
                    .description("User management service for the Nexus platform"))
            .servers(List.of(
                    new Server()
                        .url("http://localhost:8080")
                        .description("Local Development")
            ));
    }
    
}