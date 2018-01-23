package com.lyyco.rays.service.pattern;

import com.lyyco.rays.service.pattern.decorator.LowerCaseInputStream;

import java.io.*;

/**
 * Created by lyy on 2018/1/23.
 */
public class Test {
    public static void main(String[]args) throws IOException {
        int c;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
        while((c = in.read())>=0){
            System.out.println((char)c);
        }
        in.close();
    }
}
