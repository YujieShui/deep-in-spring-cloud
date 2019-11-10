package com.shuiyujie.controller;

import com.shuiyujie.service.IProductClientService;
import com.shuiyujie.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shui
 * @create 2019-11-09
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

    @Autowired
    private IProductClientService iProductClientService;

    @RequestMapping("/product/get/{id}")
    public Product getProduct(long id) {
        return  iProductClientService.getProduct(id);
    }

    @RequestMapping("/product/list")
    public List<Product> listProduct() {
        return iProductClientService.listProduct();
    }

    @RequestMapping("/product/add")
    public boolean addPorduct(Product product) {
        return  iProductClientService.addPorduct(product);
    }

}