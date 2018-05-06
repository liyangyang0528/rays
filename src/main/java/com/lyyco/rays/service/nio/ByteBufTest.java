package com.lyyco.rays.service.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;

/**
 * com.lyyco.rays.service.nio
 *
 * @Author liyangyang
 * 2018/3/23
 */
public class ByteBufTest {
    public static void main(String... args) {
        final ByteBuf heapBuf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("HI!\r\n", Charset.forName("UTF-8"))
        );
        //backing array
        if (heapBuf.hasArray()){
            byte[] array1 = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
        }

        //如何创建直接缓冲区？

        final ByteBuf directBuf = Unpooled.directBuffer();
        //direct buffers
        if(!directBuf.hasArray()){
            //获取可读字节数
            int length = directBuf.readableBytes();
            //分配新数组保存字节数据
            byte[] array2 = new byte[length];
            //复制字节数据
            directBuf.getBytes(directBuf.readerIndex(),array2);
        }
        //composite buffers
        CompositeByteBuf comBuf = (CompositeByteBuf) Unpooled.wrappedBuffer(
                Unpooled.copiedBuffer("HI!",Charset.forName("UTF-8"))
        );

        //byteBuf分配方式
        Channel channel = null;
        ByteBufAllocator allocator = channel.alloc();
        allocator.directBuffer();

        ChannelHandlerContext ctx = null;
        ByteBufAllocator allocator2 = ctx.alloc();
        allocator2.directBuffer();




    }
}
