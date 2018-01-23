package com.lyyco.rays.service.pattern.Decorator;

/**
 * 浓缩咖啡
 * Created by lyy on 2018/1/23.
 */
public class Espresso extends Beverage {
    public Espresso(){
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
