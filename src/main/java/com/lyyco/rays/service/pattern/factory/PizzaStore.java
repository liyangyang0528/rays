package com.lyyco.rays.service.pattern.factory;

/**
 * Created by lyy on 2018/1/24.
 */
public abstract class PizzaStore {

    public final Pizza orderPizza(String type){
        Pizza pizza;
        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
