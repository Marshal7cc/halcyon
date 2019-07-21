package com.marshal.halcyon.base.test.thread;

/**
 * @auth: Marshal
 * @date: 2019/7/19
 * @desc:
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new MyThread();
        //设置守护进程
        t.setDaemon(true);

        t.start();

        //主线程运行结束，守护进程也销毁
        Thread.sleep(10000);

    }

}
