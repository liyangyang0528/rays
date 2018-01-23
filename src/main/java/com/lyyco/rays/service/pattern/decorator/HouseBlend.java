package com.lyyco.rays.service.pattern.decorator;

/**
 * 另外一种咖啡
 * Created by lyy on 2018/1/23.
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
