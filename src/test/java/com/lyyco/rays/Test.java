package com.lyyco.rays;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * Author liyangyang
 * 2019/1/20
 */
public class Test implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<String> result = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        executor.execute(futureTask);
        List<Callable<String>> tasks = Lists.newArrayList();
        String future = executor.invokeAny(tasks);
        List<Future<String>> futures = executor.invokeAll(tasks);
        //将结果按可获得的顺序保存起来
        ExecutorCompletionService<String> executorCompletionService = new ExecutorCompletionService<String>(executor);
        for (Callable<String> callable : tasks) {
            executorCompletionService.submit(callable);
        }
        for (int i = 0; i < tasks.size(); i++) {
            //通过阻塞队列，实现：移除下一个已完成的结果，如果没有任何已完成的结果可用，则阻塞
            executorCompletionService.take().get();
        }
    }
}
