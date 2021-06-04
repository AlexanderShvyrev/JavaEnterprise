package com.shvrev.testing.studentplatform.integration.container;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainerEnv {
    @Container
    public static PostgresContainer postgresContainer = PostgresContainer.getInstance();
}
