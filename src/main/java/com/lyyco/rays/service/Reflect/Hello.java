package com.lyyco.rays.service.Reflect;

/**
 * com.lyyco.rays.service.Reflect
 *
 * @Author liyangyang
 * 2018/3/22
 */
public class Hello {
    public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Demo demo = new Demo();
        System.out.println(demo.getClass().getName());
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        demo1 = Class.forName("com.lyyco.rays.service.Reflect.Demo");
        demo2 = new Demo().getClass();
        demo3 = Demo.class;
        Class<?> demo4 = null;
        demo4 = Class.forName("com.lyyco.rays.service.Reflect.Person");
        Person per = (Person) demo4.newInstance();
        per.setAge(1);
        per.setName("lyy");
        System.out.println(per);

    }
}
