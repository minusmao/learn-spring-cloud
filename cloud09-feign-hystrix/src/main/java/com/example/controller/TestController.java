package com.example.controller;

import com.example.client.HystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@RestController
public class TestController {

    @Autowired
    private HystrixClient hystrixClient;

    @GetMapping("test")
    public String test() {
        System.out.println("test ok !!!");
        String demoResult = hystrixClient.demo(-1);
        System.out.println("demo result : " + demoResult);
        return "test ok ";
    }

}
