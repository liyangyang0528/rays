package com.lyyco.rays.service.thinkinginjava;

import java.nio.ByteBuffer;

/**
 * ByteBuffer从其所容纳的字节中产生各种不同基本类型值
 * Author liyangyang
 * 2018/11/30
 */
public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < bb.limit()){
            if(bb.get() != 0){
                System.out.println("nonzero");
            }
            System.out.println("i = " + i);
        }
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar())!= 0){
            System.out.println(c + "  ");
        }

        bb.rewind();
        bb.asShortBuffer().put((short)471142);


    }
}
