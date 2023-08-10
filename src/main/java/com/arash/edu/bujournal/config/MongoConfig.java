package com.arash.edu.bujournal.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import static java.util.Collections.singletonList;

@Profile("!mongo-test")
@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient, MongoConfigProperties mongoConfigProperties) {
        MongoDatabaseFactory dbFactory = new SimpleMongoClientDatabaseFactory(mongoClient, mongoConfigProperties.getDatabase());
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(dbFactory);

        MongoMappingContext mongoMappingContext = new MongoMappingContext();
        mongoMappingContext.setAutoIndexCreation(true);
        mongoMappingContext.afterPropertiesSet();

        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        mappingMongoConverter.afterPropertiesSet();

        return new MongoTemplate(dbFactory, mappingMongoConverter);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient, MongoConfigProperties mongoConfigProperties) {
        final SimpleMongoClientDatabaseFactory mongoFactory =
                new SimpleMongoClientDatabaseFactory(mongoClient, mongoConfigProperties.getDatabase());
        mongoFactory.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        return mongoFactory;
    }

    @Bean
    public MongoClient mongoClient(final MongoConfigProperties mongoConfigProperties) {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(prepareClusterSettings(mongoConfigProperties))
                .credential(prepareMongoCredentials(mongoConfigProperties))
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
                .retryWrites(false)
                .build();
        return MongoClients.create(settings);
    }

    private MongoCredential prepareMongoCredentials(final MongoConfigProperties mongoConfigProperties) {
        return MongoCredential.createCredential(
                mongoConfigProperties.getUsername(),
                mongoConfigProperties.getDatabase(),
                mongoConfigProperties.getPassword().toCharArray());
    }

    private Block<ClusterSettings.Builder> prepareClusterSettings(final MongoConfigProperties mongoConfigProperties) {
        return builder ->
                builder.hosts(
                        singletonList(
                                new ServerAddress(mongoConfigProperties.getHost(), mongoConfigProperties.getPort())));
    }
}
