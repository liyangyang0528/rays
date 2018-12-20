package com.lyyco.rays.service.thinkinginjava;

/**
 * 对多态的理解
 * Author liyangyang
 * 2018/12/20
 */
public class Polymorphism {
    static class A{
        void f(){
            System.out.println("A");
        }
    }
    static class B extends A{
        void f(){
            System.out.println("B");
        }
    }
    static class C extends A{
        void f(){
            System.out.println("C");
        }
    }
    static void check(A a){
        a.f();
    }

    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        Polymorphism.check(b);
    }
}
