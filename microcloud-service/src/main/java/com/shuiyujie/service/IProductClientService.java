package com.shuiyujie.service;

import com.shuiyujie.config.FeignClientConfig;
import com.shuiyujie.vo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author shui
 * @create 2019-11-10
 **/
@FeignClient(name = "MICROCLOUD-PROVIDER-PRODUCT",url = "http://localhost:8080",configuration = FeignClientConfig.class)
public interface IProductClientService {

    @RequestMapping("/product/get/{id}")
    Product getProduct(@PathVariable("id")long id);

    @RequestMapping("/product/list")
    List<Product> listProduct() ;

    @RequestMapping("/product/add")
    boolean addPorduct(Product product) ;

}

