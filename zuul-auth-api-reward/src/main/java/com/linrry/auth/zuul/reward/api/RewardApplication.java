package com.linrry.auth.zuul.reward.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-09-24 下午 21:02
 */
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
public class RewardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardApplication.class , args);
    }
}
