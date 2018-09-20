package com.lyyco.rays.service.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Author liyangyang
 * 2018/9/20
 */
public class UserServiceProxy implements InvocationHandler{

    public <T> T newInstance(Class<T> clz){
        /*
        1.被代理的对象的类加载器
        2.被代理的对象需要实现的接口，可以有多个
        3.方法调用的实际处理者
         */
        return (T)Proxy.newProxyInstance(clz.getClassLoader(),
                new Class[]{clz},this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }
        System.out.println("---proxy---" + method.getName());
        return null;
    }

    public static void main(String...args){
        System.out.print((char) 65);
        UserService userService = new UserServiceProxy().newInstance(UserService.class);
        userService.toString();
        userService.getUser();
    }
}
