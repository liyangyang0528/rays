package com.lyyco.rays.service.spring;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer实现了
 WebApplication-Initializer， 因此Spring会发现它， 并用它
 在Web容器中注册DelegatingFilterProxy
 * Author liyangyang
 * 2018/6/1
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer{

}
