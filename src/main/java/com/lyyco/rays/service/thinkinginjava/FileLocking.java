package com.lyyco.rays.service.thinkinginjava;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * page 566
 * 文件加锁
 * Author liyangyang
 * 2018/12/4
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("file.txt");
        /*
        对filechannel调用tryLock()或lock()
        就可以获得整个文件的fileLock
        SocketChannel DatagramChannel ServerSocketChannel不需要加锁
        因为它们是从单进程实体继承而来，通常不在两个进程之间共享网络socket
        tryLock()是非阻塞式的，它设法获取锁，如果不能获得（当其他进程已经持有相同的锁，并且不共享时）
        它将直接从方法调用返回
        lock()是阻塞的，阻塞进程直至锁可以获得，或者调用lock()的线程中断，或者调用lock的通道关闭
        使用FileLock.release()可以释放锁
         */
        FileLock fl = fos.getChannel().tryLock();
        if(null != fl){
            System.out.println("locked file");
            TimeUnit.MILLISECONDS.sleep(1000);
            fl.release();
            System.out.println("released lock");
        }
        fos.close();
    }
}
