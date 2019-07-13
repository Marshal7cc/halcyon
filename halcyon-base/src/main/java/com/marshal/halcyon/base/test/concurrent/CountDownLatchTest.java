package com.marshal.halcyon.base.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        System.out.println(System.currentTimeMillis());
                        endGate.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        startGate.countDown();
        System.out.println("endGate open" + System.currentTimeMillis());
    }

}
