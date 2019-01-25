package com.lyyco.rays.service.concurrent.jcp;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * JCP page 81 FutureTask
 * Author liyangyang
 * 2019/1/24
 */
public class Preloader {
    private class ProductInfo {
    }

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException, DataLoadException {
        try {
            //Callable表示的任务可以抛出受检查的或未受检查的异常，并且任何代码都可能抛出一个Error
            //无论任务代码抛出什么异常，都会被封装到一个ExecutionException中
            //并在Future.get()中重新被抛出
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            //先检查已知的，Callable可能会抛出的受检查异常：DataLoadExcepion，并重新抛出
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                //处理未检查异常
                throw launderThrowable(cause);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable cause) {
        if (cause instanceof RuntimeException) {
            return (RuntimeException) cause;
        } else if (cause instanceof Error) {
            throw (Error) cause;
        } else {
            throw new IllegalStateException("Not unchecked", cause);
        }
    }

    private ProductInfo loadProductInfo() {
        return null;
    }
}
