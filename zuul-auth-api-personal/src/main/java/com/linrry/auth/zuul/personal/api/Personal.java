package com.linrry.auth.zuul.personal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication(scanBasePackages = {"com.linrry.auth.zuul.personal.api"})
@EnableDiscoveryClient
public class Personal {

    public static void main(String[] args) {
        SpringApplication.run(Personal.class , args);
    }

}
