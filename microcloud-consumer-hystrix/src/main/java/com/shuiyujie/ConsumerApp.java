package com.shuiyujie;

import com.shuiyujie.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shui
 * @create 2019-11-09
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.shuiyujie.service")
@RibbonClient(name ="MICROCLOUD-PROVIDER-PRODUCT",configuration = RibbonConfig.class)
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
    }
}
