package com.shuiyujie.controller;

import com.shuiyujie.vo.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shui
 * @create 2019-11-09
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {
    public static final String PRODUCT_GET_URL = "http://localhost:8080/prodcut/get/";
    public static final String PRODUCT_LIST_URL = "http://localhost:8080/prodcut/list/";
    public static final String PRODUCT_ADD_URL = "http://localhost:8080/prodcut/add/";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/product/get")
    public Object getProduct(long id) {
        Product product = restTemplate.getForObject(PRODUCT_GET_URL + id, Product.class);
        return product;
    }

    @RequestMapping("/product/list")
    public Object listProduct() {
        List<Product> list = restTemplate.getForObject(PRODUCT_LIST_URL, List.class);
        return list;
    }

    @RequestMapping("/product/add")
    public Object addPorduct(Product product) {
        Boolean result = restTemplate.postForObject(PRODUCT_ADD_URL, product, Boolean.class);
        return result;
    }
}

