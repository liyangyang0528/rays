package com.lyyco.rays.service.pattern.factory;

import java.util.ArrayList;

/**
 * Created by lyy on 2018/1/24.
 */
public abstract class Pizza {

    String name;
    Dough dough;
    Sauce sauce;
    ArrayList toppings = new ArrayList();

    abstract void prepare();

    public  void bake(){

    }

    public  void cut(){

    }

    public  void box(){

    }

}
