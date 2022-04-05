package com.bosonit.backend.persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.bosonit.backend.persona.infrastructure",
        "com.bosonit.backend.persona.service",
        "com.bosonit.backend.persona.repository",
        "com.bosonit.backend.persona.domain"
})
@EntityScan(basePackages = "com.bosonit.backend.persona.domain")
@EnableJpaRepositories("com.bosonit.backend.persona.repository")
public class PersonaApp {

    public static void main(String[] args) {
        SpringApplication.run(PersonaApp.class, args);
    }
}
