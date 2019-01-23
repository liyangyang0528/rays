package com.lyyco.rays.service.thinkinginjava;

/**
 * Author liyangyang
 * 2019/1/23
 */
public class UseThisDemo {
    static class Person {
        public void eat(Apple apple) {
            Apple peeled = apple.getPeeled();
            System.out.println("Yummy");
        }
    }

    static class Peeler {
        static Apple peel(Apple apple) {
            //...remove peel
            return apple;
        }
    }

    static class Apple {
        Apple getPeeled() {
            return Peeler.peel(this);
        }
    }

    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
