package com.lyyco.rays.service.algorithm;

import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * Created by lyyco on 2018/4/2.
 */
public class Test {
    public static void main(String...args){
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>(16,.75f,true);
        int n = 50;
        Stack<Integer> stack = new Stack<Integer>();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }

        for (int digit : stack) {
            System.out.print(digit);
        }

        System.out.println();
    }
}
