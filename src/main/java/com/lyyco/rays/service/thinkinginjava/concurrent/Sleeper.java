package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * join()方法
 * 某个线程在另一个线程t上调用t.join()
 * 此线程将被挂起，直到目标线程t结束才恢复
 * 对join()方法的调用可以被中断，
 * 做法是在调用线程上(下例中的main()方法)调用interrupt()方法
 * Author liyangyang
 * 2018/12/12
 */
public class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }
    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            //当另外一个线程在该线程上调用interrupt()时，将给该线程设定一个标志
            //表明该线程已经被中断
            //异常被捕获时将清理这个标至
            System.out.println(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened ");
    }

    private static class Joiner extends Thread{
        private Sleeper sleeper;
        public Joiner(String name,Sleeper sleeper){
            super(name);
            this.sleeper = sleeper;
            start();
        }
        public void run(){
            try {
                //在sleeper对象上调用join()方法来等待sleeper醒来
                //如果Sleeper被中断或者正常结束,Joiner将和Sleeper一同结束
                System.out.println("调用Join:" + sleeper.getName());
                sleeper.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted ");
            }
            System.out.println(getName() + " join completed ");
        }
    }

    public static void main(String[] args) {
        //每个Sleeper都有一个Joiner
        //如果Sleeper被中断或者是正常结束，Joiner和Sleeper一同结束
        Sleeper
                sleeper = new Sleeper("Sleepy_Sleeper",15000),
                grumpy = new Sleeper("Grumpy_Sleeper",15000);
        Joiner
                dopey = new Joiner("Sleepy_Joiner",sleeper),
                doc = new Joiner("Grumpy_Joiner",grumpy);
        grumpy.interrupt();
    }
}
