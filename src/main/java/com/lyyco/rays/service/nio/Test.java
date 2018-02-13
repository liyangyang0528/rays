package com.lyyco.rays.service.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by lyy on 2018/2/13.
 */
public class Test {

    public Test() throws IOException {
        //java.IO
        ServerSocket serverSocket = new ServerSocket(8099);
        Socket cilentSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(
          new InputStreamReader(cilentSocket.getInputStream()));
        PrintWriter out = new PrintWriter(cilentSocket.getOutputStream(),true);
        String request, response;
        //readLine（）阻塞，直到读入换行符或回车符终止的字符串
        while((request = in.readLine()) != null){
            if("Done".equals(request)){
                break;
            }
//            response = processRequest(request);
//            out.println(response);
        }
        //netty Io
        Channel channel = new OioServerSocketChannel();
        //Connects asynchronously to a remote peer.
        ChannelFuture future = channel.connect(
                new InetSocketAddress("47.88.220.219",80)
        );
        //Registers a ChannelFutureListener to be notified once the operation completes
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                //If the operation is successful, creates a ByteBuf to hold the data
                if(future.isSuccess()){
                    ByteBuf buffer = Unpooled.copiedBuffer(
                            "Hello", Charset.defaultCharset()
                    );
                    //Sends the data asynchronously to the remote peer. Returns a ChannelFuture
                    ChannelFuture wf = future.channel().writeAndFlush(buffer);
                }else{
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
