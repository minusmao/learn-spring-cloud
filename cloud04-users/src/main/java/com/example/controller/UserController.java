package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 用户接口
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired  //服务注册与发现客户端对象
    private DiscoveryClient discoveryClient;


    @Autowired //负载均衡客户端对象
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("user")
    public String invokeDemo() {
        log.info("user  demo....");

        // 1.直接使用 RestTemplate 调用订单服务，服务地址必须事先硬编入代码中，且负载均衡也需自己实现
        RestTemplate myRestTemplate = new RestTemplate();
        String orderResult = myRestTemplate.getForObject("http://" + randomHost() + "/order", String.class);


        // 2.使用 ribbon 组件 + RestTemplate 实现负载均衡调用（三种方式：DiscoveryClient、LoadBalanceClient、@LoadBalance）
        // 方式 1：使用 DiscoveryClient 进行服务调用（获得所有 ORDERS 服务列表）
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("ORDERS");
        serviceInstances.forEach(serviceInstance -> log.info(
                "【方式 1】服务地址:{} 服务主机:{} 服务端口:{}",
                serviceInstance.getUri(),
                serviceInstance.getHost(),
                serviceInstance.getPort()
        ));
        String result1 = new RestTemplate().getForObject(serviceInstances.get(0).getUri() + "/order", String.class);


        //方式 2：使用 LoadBalanceClient 进行服务调用（根据负载均衡策略选取某一个服务调用，默认策略为轮询）
        ServiceInstance serviceInstance = loadBalancerClient.choose("ORDERS");    // 默认轮询
        log.info(
                "【方式 2】服务地址:{} 服务主机:{} 服务端口:{}",
                serviceInstance.getUri(),
                serviceInstance.getHost(),
                serviceInstance.getPort()
        );
        String result2 = new RestTemplate().getForObject(serviceInstance.getUri() + "/order", String.class);


        //方式 3：使用 @LoadBalanced 注解（在注入 RestTemplate 对象时，加上该注解，就具有 ribbon 负载均衡特性）
        String result3 = restTemplate.getForObject("http://ORDERS/order", String.class);

        return "ok" + result3;
    }

    /**
     * 自定义随机策略
     *
     * @return 返回随机的主机地址
     */
    public String randomHost() {
        List<String> hosts = new ArrayList<>();
        hosts.add("localhost:9100");
        hosts.add("localhost:9101");
        //生成随机数 只能在0-hosts.size()
        int i = new Random().nextInt(hosts.size());
        return hosts.get(i);
    }

}
