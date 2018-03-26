package com.lyyco.rays.service.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * netty in action
 * Created by lyyco on 2017/12/18.
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }
    public static void main (String[]args){
        args = new String[]{"8081"};
        if(args.length != 1) {
            System.err.print(
                    "Usage" + EchoServer.class.getSimpleName() + "<port>");
            return;
        }
            //set port
            int port = Integer.parseInt(args[0]);
            new EchoServer(port).start();
        }
        public void start(){
            final EchoServerHandler serverHandler = new EchoServerHandler();
            //eventLoopGroup-NioEventLoopGroup to
            // accept and handle new connections
            EventLoopGroup group = new NioEventLoopGroup();
            try{
            //serverBootstrap
            ServerBootstrap b = new ServerBootstrap();
                b.group(group)
                        //specifies the use of an NIO Transport Channel type
                        .channel(NioServerSocketChannel.class)
                        /*
                        set the local address to an InetSocketAddress with the port
                        after this the server will bind to this address
                        to listen for new connection requests
                        */
                        .localAddress(new InetSocketAddress(port))
                        /*channelInitializer---when a new connection is accepeted,
                          a new child Channel will be created
                          and the method-ChannelInitializer will add an instance of
                          your EchoServerHandler to the Channel's ChannelPipeline
                          */
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                //EchoServerHandler can always use the same one
                                socketChannel.pipeline().addLast(serverHandler);
                            }
                        });
                //bind the server asynchronously;
                //sync() waits for the bind to complete
                ChannelFuture f = b.bind().sync();
                //gets the CloseFuture of the Channel and blocks the current thread until it's complete
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    //shuts down the EventLoopGroup,releasing all resources
                    group.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
}


