package com.lyyco.rays.service.lambda;

/**
 * Created by lyy on 2018/2/23.
 */
public class Orange implements Fruit {
    private  String name;
    private  Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orange(Integer weight){
        this.weight =weight;
    }
    public Orange(String name,Integer weight){
        this.name = name;
        this.weight = weight;
    }
}
