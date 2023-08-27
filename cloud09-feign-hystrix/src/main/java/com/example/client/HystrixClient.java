package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 客户端
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@FeignClient(value = "HYSTRIX", fallback = HystrixClientFallBack.class)    // fallback: 这个属性用来指定当前调用服务不可用时，默认的备选处理
public interface HystrixClient {

    @GetMapping("/demo")
    String demo(@RequestParam("id") Integer id);

}
