package com.lyyco.rays.service.thinkinginjava;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 视图缓冲器
 * Author liyangyang
 * 2018/11/30
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(new int[]{11,32,36,77});
        System.out.println(ib.get(3));
        ib.put(3,1811);
        ib.flip();
        while (ib.hasRemaining()){
            int i = ib.get();
            System.out.println(i);
        }
    }
}
