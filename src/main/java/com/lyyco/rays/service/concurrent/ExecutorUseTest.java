package com.lyyco.rays.service.concurrent;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author liyangyang
 * 2018/5/14
 */
public class ExecutorUseTest {

    List<String> idlist = Lists.newArrayList();

    LinkedBlockingDeque<Runnable> idqueue = new LinkedBlockingDeque<>();

    ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(5,10,
            100L, TimeUnit.MINUTES,idqueue,
            new ThreadFactoryBuilder().setNameFormat("TEST").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
}
