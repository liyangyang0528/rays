package com.lyyco.rays.service.pattern.singleton;

/**
 * Created by lyy on 2018/1/24.
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton(){}

    /**
     * 双重检查锁
     * @return
     */
    public static Singleton getUniqueInstance(){
        if(uniqueInstance == null){
            synchronized (Singleton.class){
            if(uniqueInstance == null){
                uniqueInstance = new Singleton();
            }
        }}
        return uniqueInstance;
    }
}
