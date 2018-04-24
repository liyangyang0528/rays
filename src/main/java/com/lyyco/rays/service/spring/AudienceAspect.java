package com.lyyco.rays.service.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * 使用AOP
 * Author liyangyang
 * 2018/4/24
 * One of the AOP Best Practices is to deine a Common Class to store all the Pointcuts. This helps in maintaining the pointcuts at one place.
 public class CommonJoinPointConfig{}
 */
@Aspect
@Configuration
public class AudienceAspect {

    @Pointcut("execution(* com.lyyco.rays.service.spring.Performance.perform(..))")
    public void performance() {

    }

    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Around("@annotation(com.lyyco.rays.service.spring.AspectAnno)")
    public void around(ProceedingJoinPoint joinPoint){

    }


}
