package com.marshal.halcyon.base.test.thread;

/**
 * @auth: Marshal
 * @date: 2019/7/19
 * @desc:
 */
public class MyThread extends Thread {

    private int i = 0;

    @Override
    public void run() {

        try {
            while (true) {
                //正常情况需要自己去判断，让其停止，或者抛出一个异常也是可以的
                if (this.interrupted()) {
                    throw new InterruptedException();
                }
                Thread.sleep(1000);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            System.out.println("1");
        }

    }

}
