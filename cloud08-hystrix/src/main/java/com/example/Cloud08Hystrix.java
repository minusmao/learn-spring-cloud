package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hystrix 服务熔断
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@SpringBootApplication
@EnableDiscoveryClient    // 作用：通用服务注册客户端注解，代表：consul client、zk client、nacos client
@EnableEurekaClient       // 教程使用 consult 作为注册中心，但是这里依然使用 eureka 作为注册中心，所以加这个注解
@EnableCircuitBreaker     // 开启 hystrix 服务熔断
public class Cloud08Hystrix {
    public static void main(String[] args) {
        SpringApplication.run(Cloud08Hystrix.class, args);
    }
}