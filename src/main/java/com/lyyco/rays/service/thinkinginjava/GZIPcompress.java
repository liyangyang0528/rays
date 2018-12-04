package com.lyyco.rays.service.thinkinginjava;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * page 568
 * 使用GZIP进行简单压缩
 * Author liyangyang
 * 2018/12/4
 */
public class GZIPcompress {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("USAGE:error");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedOutputStream out =
                new BufferedOutputStream(
                    new GZIPOutputStream(
                        new FileOutputStream("test.gz")));
        System.out.println("test.gz");
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();
        System.out.println("Reading file");
        BufferedReader in2 =
                new BufferedReader(
                    new InputStreamReader(
                        new GZIPInputStream(
                            new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null){
            System.out.println(s);
        }
    }
}
