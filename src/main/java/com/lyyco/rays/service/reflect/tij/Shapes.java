package com.lyyco.rays.service.reflect.tij;

import java.util.Arrays;
import java.util.List;

/**
 * thinking in java 14章RTTI
 * Author liyangyang
 * 2018/10/20
 */
public class Shapes {

    public static void main(String... args) {
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
}
