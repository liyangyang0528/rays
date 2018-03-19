package com.lyyco.rays.service.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Applications implement or extend ChannelHandlers to hook into
 * the event lifecycle and provide custom application logic.
 * At least one ChannelHandler—This component implements
 * the server’s processing of data received from the client
 * its business logic
 * Created by lyy on 2018/2/13.
 */
@ChannelHandler.Sharable//标志可以被多个channel安全共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    //Called for each incoming message
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received:"+ in.toString(CharsetUtil.UTF_8));
        //将收到的消息写入发件人而不清除出站消息
        ctx.write(in);
    }

    @Override
    //Notifies the handler that the last call made to channelRead()
    // was the last message in the current batch
    public void channelReadComplete(ChannelHandlerContext ctx){
        //将挂起的消息清除到远程节点并关闭该通道
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    //Called if an exception is thrown during the read operation
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
