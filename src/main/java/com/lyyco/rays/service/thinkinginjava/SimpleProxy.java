package com.lyyco.rays.service.thinkinginjava;

/**
 * Created by lyyco on 2018/11/21.
 */
public class SimpleProxy implements Interface{
    private Interface proxied;
    public SimpleProxy(Interface proxied){
        this.proxied = proxied;
    }
    @Override
    public void doSomething() {
        System.out.println("Simple proxy dosomething");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("Simple proxy something else" + arg);
        proxied.somethingElse(arg);
    }
}
