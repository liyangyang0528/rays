package com.lyyco.rays.service.lambda;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * 一个遍历BlockingQueue并读取其中元素的Spliterator
 * 不过它并未定义如何切分流的策略，仅仅利用了流的延迟绑定能力
 * Author liyangyang
 * 2018/4/4
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

    private final BlockingQueue<T> queue;

    public BlockingQueueSpliterator(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    /**
     * 从BlockingQueue中取得原始流中的元素
     * 而这些元素最初由ForkingSteamConsumer添加
     * @param action
     * @return
     */
    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        T t;
        while (true){
            try {
                t = queue.take();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(t != ForkingStreamConsumer.END_OF_STREAM){
            action.accept(t);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
