package com.lyyco.rays.service.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by lyy on 2018/2/22.
 */
public class NettyOioServer {
    public void server(int port) throws InterruptedException {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("HI!\r\n", Charset.forName("UTF-8"))
        );
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            //create a ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //use OioEventLoopGroup to allow blocking mode
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    /*
                    Specifies ChannelInitializer that will be called
                    for each accepted connection
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    /*
                                    Add a channelInboundHandlerAdapter to
                                    intercept and handle events
                                     */
                                    new ChannelInboundHandlerAdapter() {
                                        public void channelActive(ChannelHandlerContext ctx) {
                                            ctx.writeAndFlush(buf.duplicate())
                                                    .addListener(
                                                            /*
                                                            write message to client and
                                                            add ChannelFutureListener to
                                                            close connection once message
                                                            is written
                                                             */
                                                            ChannelFutureListener.CLOSE);
                                        }
                                    }
                            );
                        }
                    });
            //bind server to accept connections
            ChannelFuture f = b.bind().sync();
            //release all resource
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
