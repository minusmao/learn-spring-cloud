package com.example.controller;

import com.example.client.ProductClient;
import com.example.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 分类接口
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@RestController
@Slf4j
public class CategoryController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/list")
    public String list() {
        return "list ok";
    }

    @GetMapping("/category")
    public String category() throws JsonProcessingException {
        log.info("category service ....");
        //1.RestTemplate 2.RestTemplate+Ribbon() 3.OpenFeign
        String result = productClient.test("小陈", 23);
        String result1 = productClient.test1(21, "xiaoming");
        String result2 = productClient.test2(new Product(1, "超短裙", 23.23, new Date()));
        String result3 = productClient.test3(new String[]{"21", "23", "24"});
        String result4 = productClient.test4(new String[]{"21", "23", "24"});
        Product product = productClient.product(21);
        log.info("product: {}", product);
        List<Product> products = productClient.findByCategoryId(1);
        products.forEach(p -> log.info("product: {}", p));

        String resultPage = productClient.findByCategoryIdAndPage(1, 5, 1);
        System.out.println(resultPage);

        //自定义json反序列化   对象转为json 序列化   on字符串转为对象
        JsonNode jsonNode = new ObjectMapper().readTree(resultPage);
        System.out.println(jsonNode.get("total"));
        Object rows = jsonNode.get("rows");
        System.out.println(rows);

        //二次json反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class);
        List<Product> productList = objectMapper.readValue(rows.toString(), collectionType);
        productList.forEach(p -> log.info("product:{}", p));

        // 测试超时
        productClient.product();

        return result;
    }

}
