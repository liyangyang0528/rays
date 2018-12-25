package com.lyyco.rays.service.thinkinginjava;

import java.io.*;

/**
 * Created by lyyco on 2018/11/29.
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("BasicFileOutput.java")
                ));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null){
            out.print(lineCount++ + ":" + s );
        }
        out.close();
    }
}
