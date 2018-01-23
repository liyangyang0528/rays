package com.lyyco.rays.service.pattern.decorator;

/**
 * 摩卡
 * 是一个装饰者,所以拓展自饮料类CondimentDecorator
 * Created by lyy on 2018/1/23.
 */
public class Mocha extends CondimentDecorator{

    Beverage beverage;//用一个实例变量记录饮料，也就是被装饰者

    /**
     * 想办法让被装饰者（饮料）被记录到实例变量中
     * 这里把饮料当做构造器参数，再由构造器将此饮料记录在实例变量中
     * @param beverage
     */
    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }

    public double cost(){
        return  0.20 + beverage.cost();
    }
}
