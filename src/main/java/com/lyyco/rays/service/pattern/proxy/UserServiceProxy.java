package com.lyyco.rays.service.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

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
        char[] c = {'a','b','c'};
        int[] i = new int[2];
        long[] l = {1L,2L,3L};
        float[] f = {1f,2f,3f};
        String[] s = {"aaa","bbb","ccc"};
        System.out.println(c instanceof char[]);
        System.out.println(c instanceof Object);
        System.out.println(c.getClass().getName());
        System.out.println(c.getClass().getSuperclass().getName());

        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        System.out.println(classStringArrayList.equals(classIntegerArrayList));
//        UserService userService = new UserServiceProxy().newInstance(UserService.class);
//        userService.toString();
//        userService.getUser();
    }
}
