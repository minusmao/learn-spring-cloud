package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    private int port;

    @GetMapping("order")
    public String demo() {
        log.info("order demo .....");
        return "order demo ok!!!,当前提供服务端口为: " + port;
    }

}
