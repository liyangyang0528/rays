package com.lyyco.rays.service.concurrent;

import java.util.function.DoubleUnaryOperator;

/**
 * java 科里化测试
 * Author liyangyang
 * 2018/6/1
 */
public class Currying {
    /**
     * 将摄氏度转换到华氏度的
     * 公式是CtoF(x) = x*9/5 + 32
     *
     * @param x
     * @param f
     * @param b
     * @return
     */
    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    /**
     * 没有一次性地向converter方法传递所有的参数x、 f和b，相反，你只是
     * 使用了参数f和b并返回了另一个方法，这个方法会接收参数x，最终返回你期望的值x * f + b
     *
     * @param f
     * @param b
     * @return
     */
    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

    DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
    double gdp = convertCtoF.applyAsDouble(1000);
}
