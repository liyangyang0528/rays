package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 对象池：
 * 管理着数量有限的对象，当要使用对象时，可以签出，在用户使用完毕时，可以签回
 * 计数信号量允许N个任务同时访问一个资源
 * 还可以将信号量看作是在向外分发使用资源的许可证，尽管实际上没有使用任何许可证对象
 * Author liyangyang
 * 2019/1/8
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        //load pool with objects that can be checked out
        for (int i = 0; i < size; ++i) {
            try {
                //assumes a default constructor
                items.add(classObject.newInstance());
            } catch (IllegalAccessException e) {
                throw new RuntimeException();
            } catch (InstantiationException e) {
                throw new RuntimeException();
            }
        }
    }
    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }
    public void checkIn(T x){
        if(releaseItem(x)){
            available.release();
        }
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if(index == -1) return false;
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
    private synchronized T getItem() {
        for(int i = 0;i<size;++i){
            if(!checkedOut[i]){
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }
}
