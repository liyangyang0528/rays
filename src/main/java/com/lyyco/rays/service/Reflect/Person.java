package com.lyyco.rays.service.Reflect;

/**
 * com.lyyco.rays.service.Reflect
 *
 * @Author liyangyang
 * 2018/3/22
 */
public class Person {
    private String name;
    private int age;
//    public Person(String name,int age){
//        this.age = age;
//        this.name = name;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
