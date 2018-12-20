package com.lyyco.rays.service.concurrent.jcp;


/**
 * 使用条件队列实现有界缓存
 * 条件队列使得一组线程能够通过某种方式来等待特定的条件变成真
 * 传统队列的元素是一个个数据，条件队列中的元素是一个个正在等待相关条件的线程
 *
 * Object.wait会自动释放锁，并请求操作系统挂起当前线程，
 * 从而使其他线程能够获得这个锁并修改对象的状态
 * 当被挂起的线程醒来时，它将在返回之前重新获取锁
 *
 * 内置条件队列并不提供公平的排队操作，而在显式的condition可以提供公平或非公平的排队操作
 *
 * Author liyangyang
 * 2018/5/30
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        //条件谓词：not full
        while (isFull()) {
            //阻塞直到not-full
            wait();
        }
        //在缓存变为非空时，为了使take解除阻塞，必须确保在每条使缓存变为非空的代码路径中都发出一个通知
        //在BoundedBuffer中，只有一条代码路径：即在put()方法之后
        doPut(v);
        //调用notify()时，JVM会从这个条件队列上等待的的多个线程中选择一个来唤醒
        //调用notifyAll()则会唤醒所有在这个条件队列上等待的线程
        notifyAll();
    }

    //使用条件通知
    //仅当缓存从空变为非空，或者从满转为非满时，才需要释放一个线程
    //并且仅当put()/take()影响到这些状态转换时，才发出通知
    public synchronized void putByConditionalNotice(V v) throws InterruptedException {
        while (isFull()){
            wait();
        }
        boolean wasEmpty = isEmpty();
        doPut(v);
        if(wasEmpty){
            notifyAll();
        }

    }

    public synchronized V take() throws InterruptedException {
        //条件谓词:not empty
        while (isEmpty()) {
            //阻塞直到not empty
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
