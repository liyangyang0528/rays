package com.lyyco.rays.service.lambda;

/**
 * Created by lyy on 2018/2/23.
 */
public class Apple implements Fruit{

    public Apple(Integer weight){
        this.weight = weight;
    }
    public Apple(String color,Integer weight){
        this.color = color;
        this.weight = weight;
    }

    private String color;
    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
