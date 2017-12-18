package com.lyyco.rays.service.nio;

/**
 * Created by lyyco on 2017/12/18.
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }
    public static void main (String[]args){
        if(args.length != 1) {
            System.err.print(
                    "Usage" + EchoServer.class.getSimpleName() + "<port>");
            return;
        }
            int port = Integer.parseInt(args[0]);
            new EchoServer(port).start();
        }
        public void start(){
           // final EchoServerHandler serverHandler = new EchoServerHandler();
           // EventLoopGroup group = new NioEventLoopGroup();


        }
}


