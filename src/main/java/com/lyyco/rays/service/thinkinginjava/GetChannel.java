package com.lyyco.rays.service.thinkinginjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO通道
 * Author liyangyang
 * 2018/11/29
 */
public class GetChannel {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        //write a file:
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        //wrap()方法将已存在的字节数组包装到bytebuffer中
        //这样的话，就不是复制底册的数组，而是把它作为所产生的ByteBuffer的存储器
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
        //add to the end of the file
        fc = new RandomAccessFile("data.txt","rw").getChannel();
        //move to the end
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();
        //read the file
        fc = new FileInputStream("data.txt").getChannel();
        //对于只读访问，必须显示地使用静态的allocate()方法来分配ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        //一旦调用read()来告知FileChannel向ByteBuffer存储字节
        //就必须调用缓冲器的flip()，让它做好让别人读取字节的准备
        fc.read(buff);
        buff.flip();
        while (buff.hasRemaining()){
            System.out.println((char)buff.get());
        }
    }

}
