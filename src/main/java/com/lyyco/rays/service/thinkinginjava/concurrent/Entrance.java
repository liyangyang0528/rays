package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 终结任务 page 692
 * Author liyangyang
 * 2018/12/13
 */
public class Entrance implements Runnable{
    private static OrnamentalGarden.Count count = new OrnamentalGarden.Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;
    public static void cancel(){
        canceled = true;
    }
    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }
    @Override
    public void run() {
        while (!canceled){
            synchronized (this){
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
    }
    public synchronized int getValue(){
        return number;
    }
    public String toString(){
        return "Entrance" + id + ":" + getValue();
    }
    public static int getTotalCount(){
        return count.value();
    }
    public static int sumEntrances(){
        int sum = 0;
        for(Entrance entrance : entrances){
            sum += entrance.getValue();
        }
        return sum;
    }
}
