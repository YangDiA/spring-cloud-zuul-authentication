package com.linrry.auth.zuul.sys.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-05-03 下午 16:03
 */
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class , args);
    }
}
