package com.lyyco.rays.service.nio;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lyy on 2018/2/22.
 */
public class PlainNioServer {
    public void serve(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ssocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ssocket.bind(address);
        /*
        open the Selector for handling channels
         */
        Selector selector = Selector.open();
        /*
        Register the ServerSocket with the Selector to accept connections
         */
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("HI!\r\n".getBytes());
        for(;;){
            /*
            waits for new events to process;
            blocks until the next incoming event
             */
            selector.select();
            /*
            Obtains all SelectionKey instances that received events
             */
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try{
                    /*
                    Checks if the event is a new connection ready to be accepted
                     */
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        /*
                        Accept client and registers it with the selector
                         */
                        client.register(selector,SelectionKey.OP_WRITE | SelectionKey.OP_READ,msg.duplicate());
                        System.out.println("Accepted connection from" + client);
                    }
                    /*
                    check if the socket is ready for writing data
                     */
                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()){
                            //writes data to the connected client
                            if(client.write(buffer) == 0){
                                break;
                            }
                        }
                        //close the connection
                        client.close();
                    }
                }catch (IOException e){
                    key.cancel();
                    key.channel().close();
                }
            }
        }

    }
}
