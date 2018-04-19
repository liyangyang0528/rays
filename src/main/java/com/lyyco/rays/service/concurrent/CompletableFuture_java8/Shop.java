package com.lyyco.rays.service.concurrent.CompletableFuture_java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Author liyangyang
 * 2018/4/19
 */
public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        //创建completableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //在另一个线程中以异步方式执行计算
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                //长时间计算的任务结束并得出结果时，设置future的返回值
                futurePrice.complete(price);
            } catch (Exception ex) {
                //错误管理机制:抛出导致失败的异常，完成这次Future操作
                //否则在计算过程中产生错误的话，用于错误提示的异常会被限制在试图计算
                //商品价格的当前线程的范围内，最终杀死该线程
                //这会导致等待get方法返回结果的客户端永久地被阻塞
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        //无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    /**
     * 采用改成方法创建
     * 与上面的方法是完全等价的
     * 提供了错误管理机制
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsyncUseFactory(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public List<String> findPrices(String product) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("four"),
                new Shop("five"));
        /**
         * 创建一个由守护线程构成的线程池
         * 程序退出时它会被回收
         * 否则java程序无法终止或退出一个正在运行中的线程
         * 最后剩下的那个线程会由于一直等待无法发生的事件而引发问题。
         */
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //守护线程，这种方式不会阻止程序的关停
                        t.setDaemon(true);
                        return t;
                    }
                });

        List<CompletableFuture<String>> priceFutures = shops
                .stream()
                //异步方式计算每种商品的价格
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + shop.getPriceAsync(product), executor
                )).collect(Collectors.toList());
        List<String> priceee =   priceFutures
                .stream()
                .map(CompletableFuture::join)//等待所有异步操作结束
                .collect(Collectors.toList());
        priceee.forEach(System.out::println);
        return priceee;
    }


    public static void main(String... args) {
        Shop shop = new Shop("BestPrice");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite");
        long invocationTime = (System.nanoTime() - start);
        System.out.println(invocationTime);
        try {
            double pricee = futurePrice.get();
            System.out.println(System.nanoTime() - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        shop.findPrices("Future+ExecutorTest");
    }

}
