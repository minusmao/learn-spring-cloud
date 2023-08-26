package com.example.client;

import com.example.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品接口客户端
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@FeignClient(value = "PRODUCT")    // value 用来书写调用服务服务 id
public interface ProductClient {

    //声明调用商品服务根据类别id查询分页查询商品信息 以及总条数
    @GetMapping("/productList")
    String findByCategoryIdAndPage(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            @RequestParam("categoryId") Integer categoryId
    );

    ///声明调用商品服务根据类别id查询一组商品信息
    @GetMapping("/products")
    List<Product> findByCategoryId(@RequestParam("categoryId") Integer categoryId);

    //声明调用根据id查询商品信息接口
    @GetMapping("/product/{id}")
    Product product(@PathVariable("id") Integer id);

    //声明调用商品服务中test4接口 传递一个list集合类型参数 test4?ids=21&ids=22
    @GetMapping("/test4")
    String test4(@RequestParam("ids") String[] ids);

    //声明调用商品服务中test3接口 传递一个数组类型 queryString /test3?ids=21&ids=22
    @GetMapping("/test3")
    String test3(@RequestParam("ids") String[] ids);

    //声明调用商品服务中test2接口 传递一个商品对象
    @PostMapping(value = "/test2")
    String test2(@RequestBody Product product);

    //声明调用商品服务中test1接口 路径传递数据
    @GetMapping("/test1/{id}/{name}")
    String test1(
            @PathVariable("id") Integer id,
            @PathVariable("name") String name
    );

    //声明调用商品服务中test?name=xxx&age=23接口传递name,age
    @GetMapping("/test")
    String test(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    );

    // 测试请求超时
    @GetMapping("/product")
    String product();

    @GetMapping("/list")
    String list();

}
