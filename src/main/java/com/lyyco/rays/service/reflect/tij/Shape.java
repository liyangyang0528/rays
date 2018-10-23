package com.lyyco.rays.service.reflect.tij;

/**
 * Author liyangyang
 * 2018/10/20
 */
abstract class Shape {
    void draw(){
        System.out.println(this + ".draw()");
    }
    abstract public String toString();
}
