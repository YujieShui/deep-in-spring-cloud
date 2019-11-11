package com.shuiyujie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shuiyujie.service.IProductService;
import com.shuiyujie.vo.Product;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shui
 * @create 2019-11-09
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private IProductService iProductService;

    /**
     * 调用Eureka的发现服务
     */
    @Resource
    private DiscoveryClient client ;

    /**
     * @HystrixCommand(fallbackMethod = "getFallback")
     * 会在发生异常的时候调用 getFallback()
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/get/{id}")
    @HystrixCommand(fallbackMethod = "getFallback")
    public Object get(@PathVariable("id") long id) {
        Product product = this.iProductService.get(id);
        if(product == null) {
            throw new RuntimeException("该产品已下架！") ;
        }
        return  product;
    }

    /**
     * 服务熔断时被调用
     *
     * @param id
     * @return
     */
    public Object  getFallback(@PathVariable("id") long id){
        Product product = new Product();
        product.setProductName("HystrixName");
        product.setProductDesc("HystrixDesc");
        product.setProductId(0L);
        return product;
    }

    @RequestMapping(value="/add")
    public Object add(@RequestBody Product product) {
        return this.iProductService.add(product) ;
    }

    @RequestMapping(value="/list")
    public Object list() {
        return this.iProductService.list() ;
    }

    @RequestMapping("/discover")
    public Object discover() { // 直接返回发现服务信息
        return this.client ;
    }

}

