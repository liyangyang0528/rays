package com.lyyco.rays.service.spring;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 *
 * 向web容器中注册其他组件
 * Author liyangyang
 * 2018/6/1
 */
public class MyServletInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic myServlet = servletContext.addServlet("myServlet",MyServlet.class);
        myServlet.addMapping("/my/servlet/**");
    }
}
