package com.lyyco.rays.service.pattern.factory;

/**
 * Created by lyy on 2018/1/24.
 */
public class NYStyleCheesePizza extends Pizza{

    PizzaIngredientFactory factory;
    public NYStyleCheesePizza(PizzaIngredientFactory factory){
        this.factory = factory;
    }

    @Override
    void prepare() {
        dough = factory.createDough();
        sauce = factory.createSauce();

    }
}
