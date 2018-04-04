package com.lyyco.rays.service.lambda;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * Author liyangyang
 * 2018/4/4
 */
public class ForkingStreamConsumer<T> implements Consumer<T>, Results {

    static final Object END_OF_STREAM = new Object();
    private final List<BlockingQueue<T>> queues;
    private final Map<Object, Future<?>> actions;

    ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
        this.queues = queues;
        this.actions = actions;
    }

    /**
     * 等待Future完成相关的计算，返回由特定键标识的处理结果
     * @param key
     * @param <R>
     * @return
     */
    @Override
    public <R> R get(Object key) {
        try {
            return ((Future<R>) actions.get(key)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将流中遍历的元素添加到所有的队列中
     * @param t
     */
    @Override
    public void accept(T t) {
        queues.forEach(q -> q.add(t));
    }

    /**
     * 将最后一个元素添加到队列中，表明该流已经结束
     */
    void finish(){
        accept((T) END_OF_STREAM);
    }
}
