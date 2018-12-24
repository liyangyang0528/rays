package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞算法队列中的插入
 * Author liyangyang
 * 2018/12/24
 */
public class LinkedQueue <E>{
    private static class Node <E>{
        final E item;
        final AtomicReference<Node<E>> next;
        public Node(E item,Node<E> next){
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
    private final Node<E> dummy = new Node<>(null,null);
    private final AtomicReference<Node<E>> head =
            new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail =
            new AtomicReference<>(dummy);

    public boolean put(E item){
        Node<E> newNode = new Node<E>(item,null);
        while (true){
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if(curTail == tail.get()){
                if(tailNext != null){
                    //队列处于静止状态，推进尾节点
                    tail.compareAndSet(curTail,tailNext);
                }else {
                    //处于静止状态，尝试插入新节点
                    if(curTail.next.weakCompareAndSet(null,newNode)){
                        //插入成功，尝试推进尾节点
                        tail.compareAndSet(curTail,newNode);
                        return true;
                    }
                }
            }
        }
    }

}
