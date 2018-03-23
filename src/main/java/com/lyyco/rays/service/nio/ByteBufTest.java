package com.lyyco.rays.service.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * com.lyyco.rays.service.nio
 *
 * @Author liyangyang
 * 2018/3/23
 */
public class ByteBufTest {
    public static void main(String... args) {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("HI!\r\n", Charset.forName("UTF-8"))
        );
        //backing array
        if (buf.hasArray()){
            byte[] array1 = buf.array();
            int offset = buf.arrayOffset() + buf.readerIndex();
            int length = buf.readableBytes();
        }
        //direct buffers
        if(!buf.hasArray()){
            int length = buf.readableBytes();
            byte[] array2 = new byte[length];
            buf.getBytes(buf.readerIndex(),array2);
        }
        //composite buffers
        CompositeByteBuf comBuf = (CompositeByteBuf) Unpooled.wrappedBuffer(
                Unpooled.copiedBuffer("HI!",Charset.forName("UTF-8"))
        );
    }
}
