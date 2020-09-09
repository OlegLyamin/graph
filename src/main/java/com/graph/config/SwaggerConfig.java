package com.graph.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class, Pageable.class);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Graph API")
                        .description("API сервиса graph")
                        .version("v0.0.1"));
    }
}
