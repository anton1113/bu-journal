package com.arash.edu.bujournal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConfigProperties {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
}
