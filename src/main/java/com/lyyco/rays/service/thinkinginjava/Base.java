package com.lyyco.rays.service.thinkinginjava;

/**
 * Author liyangyang
 * 2018/12/11
 */
abstract class Base {
    public Base(int i){
        System.out.println("Base constructor. i = " + i);
    }
    public abstract void f();
}
