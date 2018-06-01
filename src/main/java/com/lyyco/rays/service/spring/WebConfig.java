package com.lyyco.rays.service.spring;

import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Author liyangyang
 * 2018/4/19
 */
@Configuration//表明是一个配置类
@ComponentScan(basePackages = {"spring"})
@ComponentScan(basePackageClasses = {ExampleAction.class})
@Profile("dev")//这个配置类中的bean只有在dev profile激活时才会创建
@Scope(//定义BEAN的作用域为Web应用的一个会话，并指定CGLIB的代理方式
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class WebConfig {
}
