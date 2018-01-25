package com.lyyco.rays.service.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lyy on 2018/1/25.
 */
public class CglibProxyFactory implements MethodInterceptor {
    //目标对象
    private Object target;

    public CglibProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        //cglib的工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类即代理对象
        return en.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务3....");
        //执行目标对象的方法
        Object returnValue = method.invoke(target,objects);
        System.out.println("提交事务3....");
        return returnValue;
    }
}
