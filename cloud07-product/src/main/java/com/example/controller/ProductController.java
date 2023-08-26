package com.example.controller;

import com.example.entity.Product;
import com.example.model.CollectionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 商品接口
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@RestController
@Slf4j
public class ProductController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/productList")
    public Map<String, Object> findByCategoryIdAndPage(Integer page, Integer rows, Integer categoryId) {
        log.info("当前页: {} 每页显示记录数:{} 当前类别id:{} ", page, rows, categoryId);
        //根据类别id分页查询符合当前页集合数据  List<Product>   select * from t_product where categoryId=? limt ?(page-1)*rows,?(rows)
        //根据类别id查询当前类别下总条数       totalCount      select count(id) from t_product where categoryId=?
        Map<String, Object> map = new HashMap<>();
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "短裙", 23.23, new Date()));
        products.add(new Product(2, "超短裙", 23.23, new Date()));
        products.add(new Product(3, "超级超短裙", 23.23, new Date()));
        int total = 1000;
        map.put("rows", products);
        map.put("total", total);
        return map;
    }

    @GetMapping("/products")
    public List<Product> findByCategoryId(Integer categoryId) {
        log.info("类别id: {}", categoryId);
        //调用业务逻辑根据类别id查询商品列表
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "短裙", 23.23, new Date()));
        products.add(new Product(2, "超短裙", 23.23, new Date()));
        products.add(new Product(3, "超级超短裙", 23.23, new Date()));
        return products;
    }


    //定义一个接口接收id类型参数,返回一个基于id查询的对象
    @GetMapping("/product/{id}")
    public Product product(@PathVariable("id") Integer id) {
        log.info("id:{}", id);
        return new Product(id, "超短连衣裙", 23.23, new Date());
    }


    //定义一个接口接受集合类型参数
    //springmvc 不能直接接受集合类型参数,如果想要接收集合类型参数必须将集合放入对象中,使用对象的方式接收才行
    // oo: oriented(面向) object(对象) 面向对象
    // vo(value object): 用来传递数据对象称之为值对象
    // dto:(data transfer(传输) object): 数据传输对象
    @GetMapping("/test4")
    public String test4(CollectionVO collectionVO) {
        collectionVO.getIds().forEach(id -> log.info("id:{} ", id));
        return "test4 ok,当前服务端口为: " + port;
    }

    //定义个接口接受数组类型参数
    @GetMapping("/test3")
    public String test3(String[] ids) {
        for (String id : ids) {
            log.info("id: {}", id);
        }
        //手动转为list List<String> strings = Arrays.asList(ids);
        return "test3 ok,当前服务端口为: " + port;
    }

    //定义一个接受对象类型参数接口
    @PostMapping("/test2")
    public String test2(@RequestBody Product product) {
        log.info("product:{}", product);
        return "test2 ok,当前服务端口为: " + port;
    }

    //定义一个接受零散类型参数接口  路径传递参数
    @GetMapping("/test1/{id}/{name}")
    public String test1(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        log.info("id:{}    name:{}", id, name);
        return "test1 ok,当前服务端口为: " + port;
    }

    //定义一个接受零散类型参数接口 queryString
    @GetMapping("/test")
    public String test(String name, Integer age) {
        log.info("name:{}     age:{}", name, age);
        return "test ok,当前服务端口为: " + port;
    }

    // 测试请求超时
    @GetMapping("/product")
    public String product() throws InterruptedException {
        log.info("进入商品服务.....");
        Thread.sleep(2000);
        return "product ok,当前提供服务端口:" + port;
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request, String color) {
        String header = request.getHeader("User-Name");
        System.out.println("获取对应请求参数 color: " + color);
        System.out.println("获取请求头信息: " + header);
        log.info("商品列表服务");
        return "list ok当前提供服务端口:" + port;
    }

}
