package com.example.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义网关全局filter
 *
 * @author minus
 * @since 2023-08-30 00:04
 */
@Configuration
public class CustomerGlobalFilter implements GlobalFilter, Ordered {

    //类似javaweb doFilter
    //exchange : 交换   request response   封装了 request response
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //httprequest对象
        ServerHttpRequest request = exchange.getRequest();
        //httpresponse对象
        ServerHttpResponse response = exchange.getResponse();
        System.out.println("经过全局Filter处理.......");
        Mono<Void> filter = chain.filter(exchange);//放心filter继续向后执行
        System.out.println("响应回来Filter处理......");
        return filter;
    }

    //order 排序  int数字:用来指定filter执行顺序  默认顺序按照自然数字进行排序  -1 在所有filter执行之前执行
    @Override
    public int getOrder() {
        return -1;
    }

}
