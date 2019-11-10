package com.shuiyujie.controller;

import com.shuiyujie.vo.Product;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author shui
 * @create 2019-11-09
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

//    public static final String PRODUCT_GET_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/prodcut/get/";
//    public static final String PRODUCT_LIST_URL="http://MICROCLOUD-PROVIDER-PRODUCT/prodcut/list/";
//    public static final String PRODUCT_ADD_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/prodcut/add/";

    public static final String PRODUCT_TOPIC = "MICROCLOUD-PROVIDER-PRODUCT";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private HttpHeaders httpHeaders;

    /**
     * 在服务的消费方，也是可以获取到服务提供方的具体信息
     */
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/product/list")
    public  Object listProduct() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose(PRODUCT_TOPIC) ;
        System.out.println(
                "【*** ServiceInstance ***】host = " + serviceInstance.getHost()
                        + "、port = " + serviceInstance.getPort()
                        + "、serviceId = " + serviceInstance.getServiceId());

        URI uri = URI.create(String.format("http://%s:%s/product/list/" ,
                serviceInstance.getHost(), serviceInstance.getPort()));

        List<Product> list = restTemplate.exchange(uri,HttpMethod.GET,new HttpEntity<Object>(httpHeaders), List.class).getBody();
        return  list;
    }
}


