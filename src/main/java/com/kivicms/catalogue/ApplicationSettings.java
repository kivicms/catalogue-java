package com.kivicms.catalogue;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "settings")

public class ApplicationSettings {
    private String username;
    private String password;

    private String swaggerServer;
}

