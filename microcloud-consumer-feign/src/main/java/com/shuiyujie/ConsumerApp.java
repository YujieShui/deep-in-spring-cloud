package com.shuiyujie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shui
 * @create 2019-11-09
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.shuiyujie.service")
// Feign 内置 Ribbon，也可以配置负载均衡的方式
//@RibbonClient(name ="MICROCLOUD-PROVIDER-PRODUCT",configuration = RibbonConfig.class)
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
    }
}
