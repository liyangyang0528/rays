package com.lyyco.rays.service.thinkinginjava;

/**
 * 匿名内部类
 * Author liyangyang
 * 2018/12/11
 */
public class Parcel7 {
    public Contents contents(){
        return new Contents() {
            private int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}
