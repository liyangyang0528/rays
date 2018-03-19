package com.lyyco.rays.service.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * DiscardServer
 * com.lyyco.rays.service.nio
 *
 * @Author liyangyang
 * 2018/3/19
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;

    }

    public void run() throws InterruptedException {
        /*
        NioEventLoopGroup is a multithreaded event loop that handles I/O operation
        The first one, often called 'boss', accepts an incoming connection.
        The second one, often called 'worker',
        handles the traffic of the accepted connection once
        the boss accepts the connection
        and registers the accepted connection to the worker
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap is a helper class that sets up a server
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    /*
                    specify to use the NioServerSocketChannel class which is used to
                    instantiate a new Channel to accept incoming connections.
                     */
                    .channel(NioServerSocketChannel.class)
                    /*
                     The ChannelInitializer is a special handler
                     that is purposed to help a user configure a new Channel
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    //option() is for the NioServerSocketChannel that accepts incoming connections
                    .option(ChannelOption.SO_BACKLOG, 128)
                    /*
                    childOption() is for the Channels accepted by the parent
                    ServerChannel, which is NioServerSocketChannel in this case
                     */
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //bind and start to accept incoming connections
            ChannelFuture f = b.bind(port).sync();
        /*
        wait until the server socket is closed
         */
            f.channel().closeFuture().sync();


        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }
    }

    public static void main(String... args) throws InterruptedException {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}