package com.arash.edu.bujournal.init;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@SuppressWarnings("rawtype")
@Slf4j
public class TestContainersMySqlInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String IMAGE_NAME = "mysql";
    private static final String DEFAULT_IMAGE_TAG = "5.7";
    private static final String MYSQL_VERSION_PROPERTY = "bu.journal.testcontainers.mysql.version";
    private static final String MYSQL_URL_PROPERTY = "spring.datasource.url";
    private static final String MYSQL_USERNAME_PROPERTY = "spring.datasource.username";
    private static final String MYSQL_PASSWORD_PROPERTY = "spring.datasource.password";

    private static MySQLContainer<?> mysql;

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext context) {
        if (isNull(mysql) || !mysql.isCreated()) {
            startContainer(context);
        } else {
            log.info("Skipping TestContainers MySQL initialization. Container is already created. " +
                    "Updating properties.");
        }

        updateConfigurationProperties(context);
    }

    private void startContainer(ConfigurableApplicationContext context) {
        log.info("Starting TestContainers MySQL initialization.");
        mysql = new MySQLContainer<>(getDockerImageName(getMysqlVersionOrDefault(context)));
        Startables.deepStart(Stream.of(mysql)).join();
    }

    private void updateConfigurationProperties(ConfigurableApplicationContext ctx) {
        log.info("Mysql host address {}", mysql.getJdbcUrl());
        MapPropertySource containersPropertySource = new MapPropertySource("testcontainers-mysql",
                ImmutableMap.of(
                        MYSQL_URL_PROPERTY, mysql.getJdbcUrl(),
                        MYSQL_USERNAME_PROPERTY, mysql.getUsername(),
                        MYSQL_PASSWORD_PROPERTY, mysql.getPassword()
                ));
        ctx.getEnvironment().getPropertySources().addFirst(containersPropertySource);
    }

    private String getMysqlVersionOrDefault(ConfigurableApplicationContext ctx) {
        return ofNullable(ctx.getEnvironment()
                .getProperty(MYSQL_VERSION_PROPERTY))
                .orElse(DEFAULT_IMAGE_TAG);
    }

    private DockerImageName getDockerImageName(String imageTag) {
        return DockerImageName.parse(IMAGE_NAME).withTag(imageTag);
    }
}
