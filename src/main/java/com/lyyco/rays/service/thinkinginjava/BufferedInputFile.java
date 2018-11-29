package com.lyyco.rays.service.thinkinginjava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by lyyco on 2018/11/29.
 */
public class BufferedInputFile {
    public static String read(String filename) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null){
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(read("BufferedInputFile.java"));
    }
}
