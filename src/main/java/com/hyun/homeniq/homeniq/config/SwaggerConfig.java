package com.hyun.homeniq.homeniq.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger/OpenAPI 설정
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HomeniQ API")
                        .version("1.0.0")
                        .description("HomeniQ 쇼핑몰 API 문서")
                        .contact(new Contact()
                                .name("HomeniQ Team")
                                .email("contact@homeniq.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9090")
                                .description("로컬 개발 서버")
                ));
    }
}
