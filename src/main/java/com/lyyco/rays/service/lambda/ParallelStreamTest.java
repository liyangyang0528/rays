package com.lyyco.rays.service.lambda;

import java.util.stream.Stream;

/**
 * 并行流测试、理解用
 * Author liyangyang
 * 2018/4/3
 */
public class ParallelStreamTest {
    /**
     * 最后一次parallel或sequential调用会影响整个流水线
     * 并行流内部使用了默认的ForkJoinPool
     * 它默认的线 程 数 量 就 是 你 的 处 理 器 数 量
     * 这 个 值 是 由 Runtime.getRuntime().availableProcessors()得到的.
     * 修改方法System.setProperty
     * ("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     * 这是一个全局设置，因此它将影响代码中所有的并行流。反过来说，目前还无法专为某个
     并行流指定这个值
     */
    public static long parallelSum(long n){
        return Stream.iterate(1L,i-> i+1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }
}
