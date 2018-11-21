package com.lyyco.rays.service.thinkinginjava;

/**
 * Created by lyyco on 2018/11/21.
 */
public class SimpleProxyDemo {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonbo");
    }
    public static void main(String[]args){
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
