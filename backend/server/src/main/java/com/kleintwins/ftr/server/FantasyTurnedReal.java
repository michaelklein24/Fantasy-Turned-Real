package com.kleintwins.ftr.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.kleintwins.ftr.server.FantasyTurnedReal.basePackage;

@SpringBootApplication
@ComponentScan(basePackages = {basePackage})
@EnableJpaRepositories(basePackage)
@EntityScan(basePackage)
@EnableScheduling
public class FantasyTurnedReal {

    public final static String basePackage = "com.kleintwins.ftr";

    public static void main(String[] args) {
        SpringApplication.run(FantasyTurnedReal.class, args);
    }
}