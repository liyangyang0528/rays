package com.lyyco.rays.service.thinkinginjava.concurrent;

/**
 * 任务类直接从Thread继承
 * SimpleThread
 * Author liyangyang
 * 2018/12/26
 */
public class SimpleThread extends Thread{
    private int countDown = 5;
    private static int threadCount = 0;
    public SimpleThread(){
        super(Integer.toString(++threadCount));
        start();
    }
    public String toString(){
        return "#" + getName() + "(" + countDown + ")";
    }
    public void run(){
        while (true){
            System.out.println(this);
            if(-- countDown == 0){
                return;
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5;i++){
            new SimpleThread();
        }
    }

}
