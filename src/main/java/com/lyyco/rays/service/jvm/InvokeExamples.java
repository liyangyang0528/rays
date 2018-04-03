package com.lyyco.rays.service.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 几个Invoke字节码的理解
 * Author liyangyang
 * 2018/4/3
 */
public class InvokeExamples {
    private void run() {
        List ls = new ArrayList();
        ls.add("Good Day");

        ArrayList als = new ArrayList();
        als.add("Dydh Da");
    }
    public static void main(String[] args) {
        InvokeExamples sc = new InvokeExamples();
        sc.run();
    }
}
