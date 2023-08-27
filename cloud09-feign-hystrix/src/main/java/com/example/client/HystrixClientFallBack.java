package com.example.client;

import org.springframework.stereotype.Component;

/**
 * 自定义HystrixClient 默认备选处理
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@Component
public class HystrixClientFallBack implements HystrixClient {

    @Override
    public String demo(Integer id) {
        return "当前服务不可用,请稍后再试! id: " + id;
    }

}
