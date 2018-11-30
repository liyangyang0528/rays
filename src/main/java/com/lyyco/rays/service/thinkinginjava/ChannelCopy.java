package com.lyyco.rays.service.thinkinginjava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件复制程序
 * Author liyangyang
 * 2018/11/30
 */
public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("args:sourcefile destfile");
            System.exit(1);
        }
        FileChannel
                //用于读
                in = new FileInputStream(args[0]).getChannel(),
                //用于写
                out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        //返回-1时表示已经到达输入的末尾
        //每次read()操作后，就会将数据输入到缓冲器中
        while (in.read(buffer) != -1){
            //prepare for writing
            //flip()则是准备缓冲器以便它的信息可以由write()提取
            buffer.flip();
            //write()操作之后，信息仍在缓冲器中
            out.write(buffer);
            //clear()操作则对所有的内部指针重新安排
            //以便缓冲器在另一个read()操作期间能够做好接收数据的准备
            //prepare for reading
            buffer.clear();
        }
        //特殊方法transferTo()和transferFrom()允许我们将一个通道和另一个通道相连
        out.transferFrom(in,0,in.size());
    }

}
