package com.lyyco.rays.service.thinkinginjava;

/**
 * Created by lyyco on 2018/11/21.
 */
public class RealObject implements Interface{
    @Override
    public void doSomething() {
        System.out.print("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.print("doSomethingElse" + arg);
    }
}
