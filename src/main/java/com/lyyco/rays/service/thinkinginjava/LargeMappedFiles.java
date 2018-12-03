package com.lyyco.rays.service.thinkinginjava;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * page 563
 * 内存映射文件
 * @Author liyangyang
 * 2018/12/3
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF;//128 MB

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("test.dat","rw")
                .getChannel()
                //产生mappedByteBuffer,这是一种特殊类型的直接缓冲器
                .map(FileChannel.MapMode.READ_WRITE,0,length);
        for(int i = 0;i<length;i++){
            out.put((byte)'x');
            System.out.println("Finished writing");
        }
        for(int i = length /2;i<length/2+6;i++){
            System.out.println((char)out.get(i));
        }
    }
}
