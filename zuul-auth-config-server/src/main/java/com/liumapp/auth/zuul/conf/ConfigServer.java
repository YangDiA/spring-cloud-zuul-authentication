package com.liumapp.auth.zuul.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by liumapp on 3/5/18 2:16 PM.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class , args);
    }

}
