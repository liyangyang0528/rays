package com.lyyco.rays.service.thinkinginjava;

/**
 * 内部类中使用.this，生成对外部类对象的引用
 * Author liyangyang
 * 2018/12/11
 */
public class DotThis {
    void f(){
        System.out.println("DotThis.f();");
    }
    public class Inner{
        public DotThis outer(){
            //生成对外部类对象的引用
            return DotThis.this;
        }
    }
    public Inner inner(){
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
