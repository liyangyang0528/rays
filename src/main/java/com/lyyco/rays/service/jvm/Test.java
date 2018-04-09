package com.lyyco.rays.service.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 测试类
 * Author liyangyang
 * 2018/4/9
 */
public class Test {

    public static void main(String... args) {
        //引用队列
        ReferenceQueue refs = new ReferenceQueue();
        //强引用
        Object obj = new Object();
        //软引用
        SoftReference<Object> sf = new SoftReference<Object>(obj,refs);
        //弱引用
        WeakReference<Object> wf = new WeakReference<Object>(obj,refs);
        //虚引用
        PhantomReference<Object> pf = new PhantomReference<Object>(obj, refs);

    }
}
