package com.arash.edu.bujournal.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
public class TestContainersMongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String IMAGE_NAME = "mongo";
    private static final String DEFAULT_IMAGE_TAG = "6.0.7";
    private static final String MONGO_VERSION_PROPERTY = "bu.integrationtest.testcontainers.mongo.version";

    private static final String MONGO_URL_PROPERTY = "spring.data.mongodb.uri";
    private static final String MONGO_CONTAINER_HOST = "spring.data.mongodb.host";
    private static final String MONGO_CONTAINER_PORT = "spring.data.mongodb.port";

    private static final int MONGO_DB_PORT = 27017;

    private static MongoDBContainer mongoDBContainer;

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        if (isNull(mongoDBContainer) || !mongoDBContainer.isCreated()) {
            startContainer(context);
        }
        updateConfigurationProperties(context);
    }

    private void updateConfigurationProperties(ConfigurableApplicationContext ctx) {
        String mongoReplicasetUrl = mongoDBContainer.getReplicaSetUrl();
        String mongoHost = mongoDBContainer.getHost();
        int mongoPort = mongoDBContainer.getMappedPort(MONGO_DB_PORT);

        log.info("Updating properties with uri {}, host {}, and port {}", mongoReplicasetUrl, mongoHost, mongoPort);

        MapPropertySource containersPropertySource = new MapPropertySource("testcontainers-mongo",
                ImmutableMap.of(
                        MONGO_URL_PROPERTY, mongoReplicasetUrl,
                        MONGO_CONTAINER_HOST, mongoHost,
                        MONGO_CONTAINER_PORT, mongoPort
                ));
        ctx.getEnvironment().getPropertySources().addFirst(containersPropertySource);
    }

    private void startContainer(ConfigurableApplicationContext context) {
        log.info("No running Mongo TestContainer found. Starting new one");

        String imageTag = getDesiredMongoVersionOrDefault(context);
        mongoDBContainer = new MongoDBContainer(getDockerImageName(imageTag));
        Startables.deepStart(Stream.of(mongoDBContainer)).join();
    }

    private String getDesiredMongoVersionOrDefault(ConfigurableApplicationContext ctx) {
        return ctx.getEnvironment().getProperty(MONGO_VERSION_PROPERTY, DEFAULT_IMAGE_TAG);
    }

    private DockerImageName getDockerImageName(String imageTag) {
        return DockerImageName.parse(IMAGE_NAME).withTag(imageTag);
    }
}
