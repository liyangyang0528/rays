package com.lyyco.rays.service.lambda;

import java.util.function.DoubleBinaryOperator;

/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 13:03 2018/6/9
 */
public enum Operation {
//    PLUS("+") {
//        public double apply(double x, double y) {
//            return x + y;
//        }
//    },
//    MINUD("-") {
//        public double apply(double x, double y) {
//            return x - y;
//        }
//    },
    TIMES("*",(x,y)-> x * y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    Operation(String symbol, DoubleBinaryOperator op){
        this.symbol = symbol;
        this.op = op;
    }
//    public abstract double apply(double x, double y);

    public double apply(double x,double y){
        return op.applyAsDouble(x,y);
    }
}
