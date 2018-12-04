package com.lyyco.rays.service.thinkinginjava;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * page 567
 * 对映射文件的部分加锁
 * Author liyangyang
 * 2018/12/4
 */
public class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFFF;//128MB
    static FileChannel fc;

    public static void main(String[] args) throws Exception {
        fc = new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }
        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }


    private static class LockAndModify extends Thread {
        private ByteBuffer buff;
        private int start, end;

        public LockAndModify(ByteBuffer mbb, int start, int end) {
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            //作用见jdk文档
            buff = mbb.slice();
            start();
        }

        public void run() {
            try {
                //获得文件通道上的锁
                FileLock fl = fc.lock(start, end, false);
                System.out.println("LOCKED:" + "start" + "to" + end);
                //perform modification
                while (buff.position() < buff.limit() - 1) {
                    buff.put((byte) (buff.get() + 1));
                }
                fl.release();
                System.out.println("RELEASED:" + "start" + "to" + end);
            } catch (IOException e) {

            }
        }
    }

}
