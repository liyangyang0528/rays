package com.lyyco.rays.service.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置前端控制器
 * configuring dispatcherServlet
 * Author liyangyang
 * 2018/4/18
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{};
    }
    //specify configuration class
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
    //map dispatcherServlet to /
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
