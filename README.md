# feign 接口服务

这篇写的挺好 [跟我学Spring Cloud（Finchley版）-09-Feign](http://www.itmuch.com/spring-cloud/finchley-9/)

前面已经学习了Ribbon,从Eureka获取服务的实例在通过RestTemplate调用，并转换成需要的对象

```java
List<Product> list = restTemplate.exchange(PRODUCT_LIST_URL,HttpMethod.GET,new HttpEntity<Object>(httpHeaders), List.class).getBody();
```

可以发现所有的数据调用和转换都是由用户直接来完成的，我们可能不想直接访问Rest接口，如果转换回来的直接是对象而不需要直接使用RestTemplate进行转换就好了，这个时候就需要使用**Feign**了

## microcloud-service

【microcloud-service】，新建立一个microcloud-service模块，这个模块专门定义客户端的调用接口

【microcloud-service】如果要通过Feign进行远程调用，依然需要安全服务提供方的认证问题，不过在feign里面已经集成了这块功能

