package com.ric.studio.ucs.web;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Richard_yyf
 * @version 1.0 2019/3/19
 */
public class ThreadPool {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("UCS-POOL-%d").build();
    static ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static <T> List<T> addTask(List<Callable<T>> tasks) {
        List<Future<T>> futureList = null;
        List<T> results = null;
        try {
            futureList = singleThreadPool.invokeAll(tasks);
            results = new ArrayList<>();
            for (Future<T> future : futureList) {
                results.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }
}
