package com.lyyco.rays.service.thinkinginjava;

import java.lang.reflect.Proxy;

/**
 * Created by lyyco on 2018/11/21.
 */
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args){
        RealObject realObject = new RealObject();
        consumer(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[]{Interface.class},new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
