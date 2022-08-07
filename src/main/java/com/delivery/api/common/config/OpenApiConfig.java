package com.delivery.api.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "TOKEN",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "TOKEN"
)
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "TOKEN")
)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("Delivery API 문서")
                .description("");

        return new OpenAPI()
                .components(new Components())
//                .servers(servers)
                .info(info);
    }
}
