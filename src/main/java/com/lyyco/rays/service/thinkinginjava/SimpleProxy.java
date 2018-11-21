package com.lyyco.rays.service.thinkinginjava;

/**
 * Created by lyyco on 2018/11/21.
 */
public class SimpleProxy implements Interface{
    private Interface proxied;
    public SimpleProxy(Interface proxied){

    }
    @Override
    public void doSomething() {
        System.out.print("Simple proxy dosomething");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.print("Simple proxy something else" + arg);
        proxied.somethingElse(arg);
    }
}
