package com.lyyco.rays.service.thinkinginjava;

/**
 * Author liyangyang
 * 2018/12/20
 */
public class Shapes {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] s = new Shape[9];
        //Fill up the array with shapes
        for (int i = 0; i < s.length; i++) {
            s[i] = gen.next();
        }
        //make polymorphic method calls
        for (Shape shape : s) {
            shape.draw();
        }
    }
}
