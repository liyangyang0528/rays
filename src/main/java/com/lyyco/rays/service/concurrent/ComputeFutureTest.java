package com.lyyco.rays.service.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Author liyangyang
 * 2018/4/11
 */
public class ComputeFutureTest {
    public void retryQueryOrder() {

        //customize executor
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(100, 100), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        return t;
                    }
                });
        //async
        List<CompletableFuture<String>> orderFutures = orderDos
                .stream()
                .map(orderDo -> CompletableFuture.supplyAsync(
                        () -> sendOrderId(orderDo.getAlitripOrderId()),executor))
                .collect(Collectors.toList());
        //join
        orderFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
    /**
     * 发送orderid到塔内
     *
     * @param id
     * @return
     */
    private String sendOrderId(String id) {
        //
        return id;
    }

}
