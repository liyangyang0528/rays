package com.lyyco.rays.service.pattern.Decorator;

/**
 * 饮料类
 * Created by lyy on 2018/1/23.
 */
public abstract class Beverage {

    public String description = "Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

}
