package com.lyyco.rays.service.concurrent;

import java.util.concurrent.*;

/**
 * Author liyangyang
 * 2018/4/11
 */
public class FutureTest {

    public static void main(String[]args){
        ExecutorService executor = Executors.newCachedThreadPool();
        //向Executor提交一个Callable对象
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                //以异步方式在新线程中执行耗时的工作
                return doSomeLongComputation();
            }
        });
        //上面的异步操作进行的同时执行其他工作
        doSomeLongComputation();

        try {
            //获取异步操作的结果；如果最终被阻塞无法得到结果，最多等一分钟后退出
            Double result = future.get(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
    /**
     * 测试用方法，无实际意义
     * @return
     */
    private static Double doSomeLongComputation() {
        return null;
    }

    public Future<Double> getPriceAsync(String product){

        //包含计算结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //或者使用CompleteableFuture类自身提供的静态工厂方法
        CompletableFuture.supplyAsync(() -> doSomeLongComputation());
        //在另一个线程中异步执行计算
        new Thread( () -> {
            double price = doSomeLongComputation();
            //需长时间计算的任务结束并得出结果时，设置Future的返回值
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }


}
