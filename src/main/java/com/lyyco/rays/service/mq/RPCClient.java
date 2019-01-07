package com.lyyco.rays.service.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Author liyangyang
 * 2019/1/4
 */
public class RPCClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private Consumer consumer;

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.75.103.97");
        factory.setPort(5672);
        factory.setUsername("lyy");
        factory.setPassword("lijia8800");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new DefaultConsumer(channel);
        channel.basicConsume(replyQueueName,true,consumer);
    }
    public String call(String message) throws IOException {
        String response = null;
        String corrId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("",requestQueueName,props,message.getBytes());
        while (true){

        }
    }
    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) throws Exception{
        RPCClient fibRpc = new RPCClient();
        System.out.println(" [X] Requesting fib(30)");
        String response = fibRpc.call("20");
        System.out.println(" [.] Got " + response);
        fibRpc.close();
    }
}
