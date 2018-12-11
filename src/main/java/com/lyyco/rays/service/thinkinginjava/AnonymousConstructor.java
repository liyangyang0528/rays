package com.lyyco.rays.service.thinkinginjava;

/**
 * 通过实例初始化达到为匿名内部类创建一个构造器的效果
 * Author liyangyang
 * 2018/12/11
 */
public class AnonymousConstructor {
    //变量i不要求是final的，因为i被传递给匿名类的基类的构造器
    //不会在匿名类内部使用
    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Inside instance initializer");
            }
            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}
