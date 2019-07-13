package com.marshal.halcyon.base.test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class ExecutorTest {

    //固定长度的线程池，每提交一个任务就创建一个线程，直到达到最大值
    private static final Executor fixedThreadPool = Executors.newFixedThreadPool(100);

    //可缓存的线程池,如果线程池规模超过了需求，将会回收多余线程，线程数量没有限制
    private static final Executor cachedThreadPool = Executors.newCachedThreadPool();

    //单线程的Executor，确保串行执行，如果线程异常结束，会新建一个线程代替之
    private static final Executor singleThreadExecutor = Executors.newSingleThreadExecutor();

    //固定长度，以延迟或定时的方式来执行任务
    private static final Executor scheduledThreadPool = Executors.newScheduledThreadPool(100);

    public static void main(String[] args) {

        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };

        fixedThreadPool.execute(task);

    }
}
