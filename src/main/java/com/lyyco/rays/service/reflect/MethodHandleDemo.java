package com.lyyco.rays.service.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 使用方法句柄
 * MethodHandle是对可直接执行的方法（域、构造方法等）的类型引用
 * 是一个有能力安全调用方法的对象
 * Author liyangyang
 * 2018/10/23
 */
public class MethodHandleDemo {

    public static void main(String... args) {
        MethodHandle mh;
        MethodType mt = MethodType.methodType(String.class);
        //获取上下文
        MethodHandles.Lookup lk = MethodHandles.lookup();
        try {//从上下文中查找方法句柄
            
        } catch (Exception e) {

        }
        /*
        第一个传入的参数是方法的返回类型
        随后的参数是方法参数的类型（跟Class对象一样）
         */
        MethodType mtToString = MethodType.methodType(String.class);
        MethodType mtSetter = MethodType.methodType(void.class, Object.class);
        MethodType mtStringComparator = MethodType.methodType(int.class, String.class, String.class);


    }
}
