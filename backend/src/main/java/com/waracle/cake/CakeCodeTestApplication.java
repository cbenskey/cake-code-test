package com.waracle.cake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CakeCodeTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeCodeTestApplication.class, args);
    }

}
