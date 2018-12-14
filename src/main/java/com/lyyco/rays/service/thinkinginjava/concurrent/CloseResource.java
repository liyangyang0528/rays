package com.lyyco.rays.service.thinkinginjava.concurrent;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * page 697 并发
 * Author liyangyang
 * 2018/12/14
 */
public class CloseResource {

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost",8080).getInputStream();
        exec.execute(new Interrupting.IOBlocked(socketInput));
        exec.execute(new Interrupting.IOBlocked(System.in));
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing " + socketInput.getClass().getName());
        socketInput.close();//release blocked thread
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close();
    }
}
