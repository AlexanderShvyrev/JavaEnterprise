package com.shvrev.studentplatform.integration.container;

import org.testcontainers.containers.PostgreSQLContainer;

import javax.annotation.PostConstruct;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {
    private static PostgresContainer container;
    private static final String IMAGE_VERSION = "postgres:12.5";

    public static PostgresContainer getInstance(){
        if (container == null){
            container = new PostgresContainer().withInitScript("sql/init.sql");
        }
        return container;
    }

    private PostgresContainer() {
        super(IMAGE_VERSION);
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("POSTGRES_DB_URL", container.getJdbcUrl());
        System.setProperty("POSTGRES_DB_USERNAME", container.getUsername());
        System.setProperty("POSTGRES_DB_PASSWORD", container.getPassword());
    }
}
