# microcloud-security 安全模块

如果 Rest 服务不加处理暴露在公网中，那么任何人都可能调用，将会泄露信息，程序也不安全。我们可以在 Provider 上添加密码验证，这样 Consumer 进行调用时就需要先进行校验。

一般会用有一个公共的安全模块供 Provider 使用，用统一的方式进行加密和解密操作。

这里我们用到了 `spring-boot-starter-security` 在 [pom.xml](./microcloud-security/pom.xml) 中添加相关依赖。

再建立一个统一的安全配置类，这个类负责用户以及密码相关的配置：[WebSecurityConfiguration.java](./microcloud-security/src/main/java/com/shuiyujie/WebSecurityConfiguration.java)。

最后，在 Provider 的 pom 文件中引用我们自定义的安全模块。