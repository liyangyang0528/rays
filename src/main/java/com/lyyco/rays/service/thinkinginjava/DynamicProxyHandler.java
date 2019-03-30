package com.lyyco.rays.service.thinkinginjava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lyyco on 2018/11/21.
 */
public class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;
    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+ proxy.getClass()+"method:"+method.getName()+"args:"+args);
        if(null != args){
            for(Object arg : args){
                System.out.println(" " + arg);
            }
        }
        return method.invoke(proxied,args);
    }

}
