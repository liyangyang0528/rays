package com.lyyco.rays.service.lambda;

import java.util.Arrays;
import java.util.Date;
import javax.swing.Timer;
/**
 * Created by lyyco on 2018/1/10.
 */
public class test {
    public static void main(String[]args){
        String[] plantes = new String[]{
          "Mercury","Venus","Earth"
        };
        Arrays.sort(plantes,(first,second)-> first.length()-second.length());
        Timer t = new Timer(1000,event->System.out.println("The time is "+new Date()));
            t.start();

    }
}

