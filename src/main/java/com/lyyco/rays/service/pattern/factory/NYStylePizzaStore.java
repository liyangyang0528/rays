package com.lyyco.rays.service.pattern.factory;

/**
 * Created by lyy on 2018/1/24.
 */
public class NYStylePizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory factory = new NYPizzaIngredientFactory();
        if("cheese".equals(type)){
            pizza = new NYStyleCheesePizza(factory);
        }
        return pizza;
    }
}
