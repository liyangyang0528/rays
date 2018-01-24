package com.lyyco.rays.service.pattern.factory;

/**
 * Created by lyy on 2018/1/24.
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public Dough createDough() {
        return new NYDough();
    }

    @Override
    public Sauce createSauce() {
        return new NYSauce();
    }

    @Override
    public Cheese createCheese() {
        return new NYCheese();
    }
}
