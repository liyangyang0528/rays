package com.lyyco.rays.service.mq;

import com.rabbitmq.client.*;

/**
 * Author liyangyang
 * 2019/1/4
 */
public class RabbitmqDemo {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.75.103.97");
        factory.setPort(5672);
        factory.setUsername("lyy");
        factory.setPassword("lijia8800");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //订阅消息
//        try {
//            channel.exchangeDeclare("ota-hotel-notify-exchange", "fanout", true);
//            String queueName = channel.queueDeclare("ota-hotel-notify-queue", true, false, false, null).getQueue();
//            channel.queueBind(queueName, "ota-hotel-notify-exchange", "");
//
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String message = new String(delivery.getBody(), "UTF-8");
//                System.out.println(message);
//            };
//            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
//            });
//        } finally {
//            channel.close();
//        }
        //RPC实现
        String callbackQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .replyTo(callbackQueueName)
                .build();
        channel.basicPublish("","rpc_queue",props,"rpc_test".getBytes());

    }
}