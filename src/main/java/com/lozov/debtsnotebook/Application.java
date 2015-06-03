package com.lozov.debtsnotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by lozov on 29.05.15.
 */
@SpringBootApplication
@Import(value = ConfigWithMySql.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigWithMySql.class, args);
    }
}
