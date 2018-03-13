package com.lyyco.rays.service.lambda;/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 14:34 2018/3/11
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 匿名类谜题
 * com.lyyco.rays.service.lambda
 *
 * @Author liyangyang
 * 2018/3/11
 */
public class MeaningOfThis {
    public final int value =4;
    public void doIt(){
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);

            }
        };
        r.run();
    }
    public static void main(String...args){
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
