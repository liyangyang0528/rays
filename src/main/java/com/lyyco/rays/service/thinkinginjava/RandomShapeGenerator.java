package com.lyyco.rays.service.thinkinginjava;

import java.util.Random;

/**
 * Author liyangyang
 * 2018/12/20
 */
public class RandomShapeGenerator {
    private Random rand = new Random(47);

    public Shape next() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Circle();
            case 1:
                return new Square();
            case 2:
                return new Triangle();
        }
    }
}
