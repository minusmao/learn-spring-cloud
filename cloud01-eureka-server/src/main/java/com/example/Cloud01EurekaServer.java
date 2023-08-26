package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka 服务
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@SpringBootApplication
@EnableEurekaServer
public class Cloud01EurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(Cloud01EurekaServer.class, args);
    }
}