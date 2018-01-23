package com.lyyco.rays.service.pattern.Decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;

/**
 * Created by lyy on 2018/1/23.
 */
public class StarbuzzCoffee {
    public static void main(String[]args){
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+"$"+beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        System.out.println(beverage1.getDescription()+"$"+beverage1.cost());


    }
}
