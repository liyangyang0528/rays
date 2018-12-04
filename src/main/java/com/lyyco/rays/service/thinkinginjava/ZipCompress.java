package com.lyyco.rays.service.thinkinginjava;

import java.io.*;
import java.util.zip.*;

/**
 * page 569
 * 使用ZIP进行多文件保存
 * Author liyangyang
 * 2018/12/4
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException{
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f,new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("a test of java zipping");
        for(String arg : args){
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1){
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi,new Adler32());
        //TODO 后续没看明白，暂停

    }
}
