package com.lyyco.rays.service.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * SOCKET
 * com.lyyco.rays.service.nio
 *
 * @Author liyangyang
 * 2018/3/18
 */
public class SocketTest {
    public static void main(String...args){
        try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13)) {
            InputStream inputStream = s.getInputStream();
            Scanner in = new Scanner(inputStream);
            while (in.hasNext()){
                String line = in.nextLine();
                System.out.println(line);
            }

            InetAddress[] address = InetAddress.getAllByName("google.com");
            InetAddress host = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
