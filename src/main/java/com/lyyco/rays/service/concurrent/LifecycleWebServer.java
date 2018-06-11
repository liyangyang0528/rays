package com.lyyco.rays.service.concurrent;

import sun.misc.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 支持关闭操作的WEB服务器
 *
 * Author liyangyang
 * 2018/6/11
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newFixedThreadPool(4);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()){
            try{
                final Socket conn = socket.accept();
                exec.execute(()-> handleRequest(conn));
            }catch (RejectedExecutionException e){
                if(!exec.isShutdown())
                    System.out.println("task submission rejected");
            }
        }
    }
    /*
    程序中调用stop可以关闭web服务器
     */
    public void stop(){
        exec.shutdown();
    }
    private void handleRequest(Socket conn) {
//        Request req = readRequest(conn);
        /*
        此处是指以客户端请求形式向web服务器
        发送一个特定格式的HTTP请求
         */
//        if(isShutdownRequest(req))
//            stop();
//        else
//            dispatchRequest(req);

    }
}
