package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Eureka 客户端
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@SpringBootApplication
@EnableEurekaClient
public class Cloud02EurekaClient {
    public static void main(String[] args) {
        SpringApplication.run(Cloud02EurekaClient.class, args);
    }
}