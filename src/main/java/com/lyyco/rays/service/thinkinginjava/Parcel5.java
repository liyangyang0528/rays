package com.lyyco.rays.service.thinkinginjava;


import java.util.Iterator;

/**
 * 局部内部类
 * Author liyangyang
 * 2018/12/11
 */
public class Parcel5 {
    /**
     *
     * @param s
     * @return
     */
    public Destination destination(String s){
        //PDestination类是destination()方法的一部分，而不是Parcel5的一部分
        class PDestination implements Destination{
            private String label;
            private PDestination(String whereTo){
                label = whereTo;
            }
            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
    }
}
