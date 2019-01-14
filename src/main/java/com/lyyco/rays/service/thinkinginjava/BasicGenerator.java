package com.lyyco.rays.service.thinkinginjava;

/**
 * 生成某个类的对象
 * 这个类必须：
 * 1.public 的类
 * 2.具备默认的构造器
 * Author liyangyang
 * 2019/1/14
 */
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            //assumes type is a public class
            return type.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        } catch (InstantiationException e) {
            throw new RuntimeException();
        }
    }
    //produce a default generator given a type token
    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}
