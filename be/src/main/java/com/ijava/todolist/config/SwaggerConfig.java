package com.ijava.todolist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI todoListOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo-list API")
                        .description("Todo-list API Documentation")
                        .version("1.0")
                );

    }

}
