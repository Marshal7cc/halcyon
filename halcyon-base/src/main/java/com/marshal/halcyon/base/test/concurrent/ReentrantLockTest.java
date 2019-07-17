package com.marshal.halcyon.base.test.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auth: Marshal
 * @date: 2019/7/16
 * @desc:
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        //一定要释放锁
        lock.lockInterruptibly();
        try {

        } finally {
            lock.unlock();
        }
    }
}
