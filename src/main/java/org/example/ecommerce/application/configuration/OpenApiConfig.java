package org.example.ecommerce.application.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"dev", "test"})
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi(
            @Value("${open.api.title}") String title,
            @Value("${open.api.version}") String version,
            @Value("${open.api.description}") String description,
            @Value("${open.api.license}") String license,
            @Value("${open.api.licenseUrl}") String licenseUrl,
            @Value("${open.api.serverUrl}") String serverUrl,
            @Value("${open.api.serverDescription}") String serverDescription
    ) {
        return new OpenAPI()
                .info(
                        new Info().title(title)
                                  .version(version)
                                  .description(description)
                                  .license(new License().name(license)
                                                        .url(licenseUrl))
                )
                .servers(
                        List.of(
                                new Server().url(serverUrl)
                                            .description(serverDescription),
                                new Server().url("https://ecommerce.herokuapp.com")
                                            .description("Production server")
                        )
                );
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                             .group("api-service-1")
                             .packagesToScan("com.example.ecommerce.api")
                             .build();
    }
}
