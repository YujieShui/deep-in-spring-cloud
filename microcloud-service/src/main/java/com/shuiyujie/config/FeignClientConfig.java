package com.shuiyujie.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shui
 * @create 2019-11-10
 **/
@Configuration
public class FeignClientConfig {

    /**
     * 配置 feign 提供的安全认证功能
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
}

