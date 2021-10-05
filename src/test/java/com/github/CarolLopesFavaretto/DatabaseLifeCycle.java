package com.github.CarolLopesFavaretto;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class DatabaseLifeCycle implements QuarkusTestResourceLifecycleManager {

    private static MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql:8.0.19");
    @Override
    public Map<String, String> start() {
        MYSQL.start();
        Map<String, String> propriedades = new HashMap<>();
        propriedades.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", MYSQL.getUsername());
        propriedades.put("quarkus.datasource.password", MYSQL.getPassword());
        return propriedades;
    }

    @Override
    public void stop() {
        if(MYSQL != null){
            MYSQL.stop();
        }
    }
}
