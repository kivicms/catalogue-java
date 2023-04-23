package com.kivicms.catalogue;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@EnableConfigurationProperties({ApplicationSettings.class})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueApplication.class, args);
    }

    @Bean
    @Autowired
    public OpenAPI customOpenApi(ApplicationSettings applicationSettings) {
        List<SecurityRequirement> securityRequirements = new ArrayList<>();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("JWT");
        securityRequirements.add(securityRequirement);
        SecurityScheme scheme = new SecurityScheme();
        scheme.setType(SecurityScheme.Type.HTTP);
        scheme.setScheme("bearer");
        scheme.setBearerFormat("JWT");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT", scheme))
                .info(new Info()
                        .title("API of housing certificates service")
                        .version("0.1"))
                .security(securityRequirements);
    }
}
