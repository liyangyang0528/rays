package com.lyyco.rays.service.pattern.proxy;

import java.util.HashMap;
import java.util.concurrent.Executor;

/**
 * Author liyangyang
 * 2018/9/20
 */
public class HelloConcrete {
    public String sayHello(String str){
        return "HelloConcrete:"+str;
    }
    public static void main(String...args){
    }
}
