package com.shuiyujie.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * RestTemplate 配置类
 * 在获取RestTemplate对象的时候加入Ribbon的配置信息
 *
 * @author shui
 * @create 2019-11-09
 **/
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced // @LoadBalanced 注解
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }

    @Bean
    public HttpHeaders getHeaders() { // 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders(); // 定义一个HTTP的头信息
        String auth = "root:root"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }

    /**
     * 更改负载均衡策略，默认轮询改为随机(全局)
     * 单独配置 Ribbon 路由用 RibbonConfig
     * @return
     */
//    @Bean
//    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
//        return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
//    }


}

