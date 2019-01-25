package com.lyyco.rays.service.thinkinginjava;



/**
 * 初始化顺序
 * 在类的内部，变量定义的先后顺序决定了初始化的顺序
 * 即使变量定义散布于方法定义之间，
 * 它们仍旧会在任何方法（包括构造器）被调用之前得到初始化
 * Author liyangyang
 * 2019/1/23
 */
public class OrderOfInitialization {
    static class Window{
        Window(int marker){
            System.out.println("Window(" + marker + ")");
        }
    }
    static class House{
        Window w1 = new Window(1);//before constructor
        House(){
            System.out.println("House()");
            w3 = new Window(33);
        }
        Window w2 = new Window(2);//after constructor
        void f(){
            System.out.println("f():");
        }
        Window w3 = new Window(3);//at end
    }

    public static void main(String[] args) {
        House h = new House();
        h.f();
    }
}
