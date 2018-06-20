package com.lyyco.rays.service.effective;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Builder pattern is well suited to class hierarchies. Use a parallel
 * Created by lyyco on 2018/6/19.
 */
public abstract class Pizza {
    public enum Topping{HAM,MUSHROOM,ONION}
    final Set<Topping> toppings;
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();
        protected abstract T self();
    }
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }
}
