package com.shuiyujie.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 * @author shui
 * @create 2019-11-10
 **/
public class RibbonConfig {

    /**
     * 其中IRule就是所有规则的标准
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        // 随机的访问策略
        return new com.netflix.loadbalancer.RandomRule();
    }
}
