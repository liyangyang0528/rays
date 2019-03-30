package com.lyyco.rays.service.thinkinginjava;

import com.google.common.collect.Lists;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by lyyco on 2018/11/21.
 */
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args){
        List<Object> l1 = Lists.newArrayList();
        List<? extends Object> l2 = Lists.newArrayList();
        List<String> l3 = Lists.newArrayList();
        l2 = l1;
        l2 = l3;
    //        l3 = l1;
    //        l1 = l3;
        List<String> [] ls;
        List[] la = new List[10];
        ls = la;

        RealObject realObject = new RealObject();
        consumer(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[]{Interface.class},new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
