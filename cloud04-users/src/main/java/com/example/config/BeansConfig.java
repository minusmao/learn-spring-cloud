package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@Configuration
public class BeansConfig {

    @Bean
    @LoadBalanced    // 整合 restTemplate + ribbon 实现负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
