package com.arash.edu.bujournal.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import static java.util.Collections.singletonList;

@Configuration
@Slf4j
@Profile("mongo-test")
public class TestMongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String testContainerMongoHost;
    @Value("${spring.data.mongodb.port}")
    private int testContainerMongoPort;

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient, MongoConfigProperties mongoConfigProperties) {
        MongoDatabaseFactory dbFactory = new SimpleMongoClientDatabaseFactory(mongoClient, mongoConfigProperties.getDatabase());

        MongoMappingContext mongoMappingContext = new MongoMappingContext();
        mongoMappingContext.setAutoIndexCreation(true);
        mongoMappingContext.afterPropertiesSet();

        return new MongoTemplate(dbFactory);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient, MongoConfigProperties mongoConfigProperties) {
        final SimpleMongoClientDatabaseFactory mongoFactory =
                new SimpleMongoClientDatabaseFactory(mongoClient, mongoConfigProperties.getDatabase());
        mongoFactory.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        return mongoFactory;
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
                .applyToClusterSettings(builder -> builder.hosts(
                        singletonList(new ServerAddress(testContainerMongoHost, testContainerMongoPort))))
                .build();
        return MongoClients.create(settings);
    }
}
