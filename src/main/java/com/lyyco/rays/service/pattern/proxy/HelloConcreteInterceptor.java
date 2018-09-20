package com.lyyco.rays.service.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Author liyangyang
 * 2018/9/20
 */
public class HelloConcreteInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o,
                            Method method,
                            Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("you said:" + Arrays.toString(objects));
        return methodProxy.invokeSuper(objects,objects);

    }
    public static void main(String...args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new HelloConcreteInterceptor());
        HelloConcrete helloConcrete = (HelloConcrete) enhancer.create();
        System.out.println(helloConcrete.sayHello("hello cglib proxy"));
    }
}
