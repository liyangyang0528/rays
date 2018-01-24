package com.lyyco.rays.service.pattern.factory;

/**
 * Created by lyy on 2018/1/24.
 */
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();

}
