package com.linrry.auth.zuul.reward.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-09-24 下午 21:02
 */
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
@EnableCircuitBreaker
public class RewardApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RewardApplication.class , args);
    }
}
