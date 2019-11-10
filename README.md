# ribbon

Provider 可以通过 Eureka 进行注册，Consumer 可以使用 Ribbon 连上 Eureka 进行消费，并且通过 Ribbon 配置负载均衡策略。

`spring-cloud-starter-ribbon` 中包含了 Ribbon 的依赖，所以使用 Ribbon 我们只要引入 `spring-cloud-starter-ribbon` 依赖就可以了。

## Ribbon 基本实现

引入依赖之后，

- 我们在 Consumer 的 RestTemplate 的配置类中对 Ribbon 的负载均衡策略进行配置
- 修改application.yml文件，增加Eureka服务注册相关信息
- 修改项目启动类，增加Eureka客户端的配置注解
- 修改ConsumerProductController控制器

## Ribbon 负载均衡实现

创建三个 Provider 让它们分别连接不同的数据库，再对外提供服务。

现在发现每一次获取数据都是通过不同的微服务获得的，所以现在同一个消费端就可以通过 Ribbon 实现了负载均衡配置处理。

## 自定义全局配置

默认轮询，修改为随机

```java
@Bean
    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
        return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
    }
```

## 单独设置某个 Ribbon 的路由

有时候，某个消费者可能需要访问多个多个服务提供方，而希望每个服务提供方提供的路由规则并不相同，这个时候就不能让Spring扫描到IRULE，需要通过@RibbonClient 来指定服务于配置的关系

## 脱离 Eureka 使用 Ribbon

之前所用Ribbon都是从Eureka中获取服务并通过@LoadBalanced来实现负载均衡的，其实Ribbon也可以脱离Eureka来使用




