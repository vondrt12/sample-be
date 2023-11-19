package eu.greyson.sample.shared.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Greyson sample application",
        description = "API documentation of project SAMPLE.",
        license = @License(
            name = "All rights reserved (c) 2020"
        ),
        contact = @Contact(
            name = "Greyson",
            url = "http://www.greyson.eu",
            email = "info@greyson.eu"
        ),
        version = "${info.app.version}"
    )
)
@Configuration
public class OpenApiDocumentationConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToExclude("/api/dev/**")
            .build();
    }
}
