package com.liumapp.auth.zuul.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by liumapp on 2/8/18 4:06 PM.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.liumapp.auth.zuul.token"})
@MapperScan("com.liumapp.auth.zuul.token.mapper")
public class TokenManager {

    public static void main(String[] args) {
        SpringApplication.run(TokenManager.class , args);
    }

}
