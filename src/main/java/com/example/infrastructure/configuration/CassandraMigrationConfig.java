package com.example.infrastructure.configuration;

import com.datastax.oss.driver.api.core.CqlSession;

import org.cognitor.cassandra.migration.Database;
import org.cognitor.cassandra.migration.MigrationConfiguration;
import org.cognitor.cassandra.migration.MigrationRepository;
import org.cognitor.cassandra.migration.MigrationTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(
        basePackages = "com.example.infrastructure.persistence.repository"
)
@Configuration
public class CassandraMigrationConfig {

    @Value("${spring.cassandra.keyspace-name}")
    private String keyspace;

    @Bean
    CommandLineRunner cassandraMigrationRunner(CqlSession session) {
        return args -> {
            MigrationConfiguration config = new MigrationConfiguration().withKeyspaceName(keyspace);
            Database database = new Database(session, config);
            MigrationRepository repository = new MigrationRepository("cassandra/migrations");
            new MigrationTask(database, repository)
                    .migrate();
        };
    }

    @Bean
    public CassandraTemplate cassandraTemplate(CqlSession session) {
        return new CassandraTemplate(session);
    }
}