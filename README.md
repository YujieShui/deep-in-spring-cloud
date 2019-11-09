# microcloud-eureka 注册中心

Eureka 是 Netflix 的开源项目，Spring Cloud 使用其作为注册中心，实现服务的注册和发现。

## Eureka Server

引入 Eureka 的依赖

```xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
```

配置端口号，主机名

```yaml
server:
  port: 7001
eureka:
  instance: # eureak实例定义
    hostname: localhost # 定义 Eureka 实例所在的主机名称
```

启动类

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
```

## Eureka Client

添加依赖

```xml
<!-- Eureka 配置 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```

application.yml

```yaml
eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka
```

启动类开启 `@EnableEurekaClient`

```java
@SpringBootApplication
@MapperScan("com.shuiyujie.mapper")

public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }
}
```

给一个应用名

```yaml
spring:
  application:
    name: microcloud-provider-product
```

给一个主机名称

```yaml
eureka:
  ...
  instance:
    instance-id: microcloud-provider-product
```

## 查看状态信息

如果想看状态信息需要增加actuator模块

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

追加 info

```yaml
info:
  app.name: microcloud-provider-product
  company.name: enjoy
  build.artifactId: $project.artifactId$
  build.modelVersion: $project.modelVersion$
```

注意：由于在yml文件中使用了$，这个时候启动是会报错的，因此还需要一个maven-resources-plugin插件的支持

见 [pom.xml](./microcloud-provider-product/pom.xml)。

## 其他配置

安全模式、心跳检测等

[eureka application.yml](./microcloud-eureka/src/main/resources/application.yml)

[product application.yml](./microcloud-provider-product/src/main/resources/application.yml)

发现服务项，在 Provider 中添加服务发现的配置 `@EnableDiscoveryClient`，在 Controller 中添加：

```java
@RestController
@RequestMapping("/prodcut")
public class ProductController {

    ...

    @Resource
    private DiscoveryClient client ; // 进行Eureka的发现服务
    ...
    
    @RequestMapping("/discover")
    public Object discover() { // 直接返回发现服务信息
        return this.client ;
    }
}
```

## 安全机制

一般情况下Eureka 和服务的提供注册者都会在一个内网环境中，但免不了在某些项目中需要让其他外网的服务注册到Eureka，这个时候就有必要让Eureka增加一套安全认证机制了，让所有服务提供者通过安全认证后才能注册进来

```xml
<!-- 引入SpringSecurity的依赖包，作为 Eureka 的安全机制-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

【microcloud-eureka】修改 application.yml文件，增加用户、密码验证

```yaml
spring:
  security:
    user:
      name: admin
      password: admin
```

【microcloud-provider-product】修改application.yml文件，增加验证信息，用 curl 的形式访问

```yaml
eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://admin:enjoy@localhost:7001/eureka
```

【microcloud-eureka】新增配置类EurekaSecurityConfig，重写configure方法，把csrf劫持关闭


