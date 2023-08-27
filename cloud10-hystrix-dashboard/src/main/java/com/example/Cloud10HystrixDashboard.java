package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix 仪表盘
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@SpringBootApplication
@EnableDiscoveryClient    // 默认只要引入 discovery client 依赖 该注解无须显示声明
@EnableEurekaClient       // 教程使用 consult 作为注册中心，但是这里依然使用 eureka 作为注册中心，所以加这个注解
@EnableHystrixDashboard   // 开启当前应用为仪表盘应用
public class Cloud10HystrixDashboard {
    public static void main(String[] args) {
        SpringApplication.run(Cloud10HystrixDashboard.class, args);
    }
}