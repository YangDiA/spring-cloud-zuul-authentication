package com.liumapp.auth.zuul.personal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by liumapp on 3/5/18 2:57 PM.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@SpringBootApplication(scanBasePackages = {"com.liumapp.auth.zuul.personal.api"})
@EnableDiscoveryClient
public class Personal {

    public static void main(String[] args) {
        SpringApplication.run(Personal.class , args);
    }

}
