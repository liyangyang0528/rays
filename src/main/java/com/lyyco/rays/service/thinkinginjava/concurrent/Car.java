package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程之间的协作 page 704
 * Author liyangyang
 * 2018/12/17
 */
public class Car {
    private boolean waxOn = false;
    public synchronized void waxed(){
        waxOn = true;//ready to buff
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;//ready for another coat of wax
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false){
            wait();
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true){
            wait();
        }
    }
    static class WaxOn implements Runnable{
        private Car car;
        public WaxOn(Car c){
            car = c;
        }
        @Override
        public void run() {
            try{
                while (!Thread.interrupted()){
                    System.out.println("Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt ");
            }
            System.out.println("Ending Wax On task");
        }
    }
    static class WaxOff implements Runnable{
        private Car car;
        public WaxOff(Car c){
            car = c;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    car.waitForBuffing();
                    System.out.println("Wax Off");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax Off task");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(8);
        exec.shutdown();
    }
}
