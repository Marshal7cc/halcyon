package com.marshal.halcyon.base.test.concurrent;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class PrimeGenerator implements Runnable {

    private volatile boolean canceled = false;

    public void cancel() {
        this.canceled = true;
    }


    @Override
    public void run() {
        while (!canceled) {
            //do sth
        }
    }
}
