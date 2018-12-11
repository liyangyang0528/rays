package com.lyyco.rays.service.thinkinginjava;

/**
 * 在.new表达式中提供对其他外部类对象的引用
 * Author liyangyang
 * 2018/12/11
 */
public class DotNew {
    public class Inner{}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        //不能声明 dn.new DotNew.Inner()
        DotNew.Inner dni = dn.new Inner();
    }
}
