package com.lyyco.rays.service.reflect.tij;


import static edu.princeton.cs.algs4.StdOut.print;

/**
 * Author liyangyang
 * 2018/10/20
 */
public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("Class name:" + cc.getName() +
                "is interface? [" + cc.isInterface() + "]");
        print("Simple name:" + cc.getSimpleName());
        print("Canonical name:" + cc.getCanonicalName());
    }
    public static void main(String...args){
        Class c = null;
        try {
            c = Class.forName("com.lyyco.rays.service.reflect.tij.FancyToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for(Class face : c.getInterfaces()){
            printInfo(face);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try{
            obj = up.newInstance();
        } catch (IllegalAccessException e) {
            print("cannot access");
        } catch (InstantiationException e) {
            print("cannot instantiate");
            System.exit(1);
        }
    }


}
